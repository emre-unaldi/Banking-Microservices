package unaldi.invoiceservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.repository.InvoiceRepository;
import unaldi.invoiceservice.service.abstracts.InvoiceService;
import unaldi.invoiceservice.service.abstracts.mapper.InvoiceMapper;
import unaldi.invoiceservice.utils.RabbitMQ.enums.LogType;
import unaldi.invoiceservice.utils.RabbitMQ.enums.OperationType;
import unaldi.invoiceservice.utils.RabbitMQ.producer.LogProducer;
import unaldi.invoiceservice.utils.RabbitMQ.request.LogRequest;
import unaldi.invoiceservice.utils.client.UserServiceClient;
import unaldi.invoiceservice.utils.client.dto.RestResponse;
import unaldi.invoiceservice.utils.client.dto.UserResponse;
import unaldi.invoiceservice.utils.constant.ExceptionMessages;
import unaldi.invoiceservice.utils.constant.Messages;
import unaldi.invoiceservice.utils.exception.customExceptions.InvoiceNotFoundException;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.Result;
import unaldi.invoiceservice.utils.result.SuccessDataResult;
import unaldi.invoiceservice.utils.result.SuccessResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserServiceClient userServiceClient;
    private final LogProducer logProducer;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserServiceClient userServiceClient, LogProducer logProducer) {
        this.invoiceRepository = invoiceRepository;
        this.userServiceClient = userServiceClient;
        this.logProducer = logProducer;
    }

    @Override
    public DataResult<InvoiceDTO> save(InvoiceSaveRequest invoiceSaveRequest) {
        userServiceClient.findById(invoiceSaveRequest.userId());

        Invoice invoice = InvoiceMapper.INSTANCE.convertToSaveInvoice(invoiceSaveRequest);
        this.invoiceRepository.save(invoice);

        logProducer.sendToLog(prepareLogRequest(OperationType.POST,Messages.INVOICE_CREATED));

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice),
                Messages.INVOICE_CREATED
        );
    }

    @Override
    public DataResult<InvoiceDTO> update(InvoiceUpdateRequest invoiceUpdateRequest) {
        userServiceClient.findById(invoiceUpdateRequest.userId());

        if(!this.invoiceRepository.existsById(invoiceUpdateRequest.id()))
            throw new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND);

        Invoice invoice = InvoiceMapper.INSTANCE.convertToUpdateInvoice(invoiceUpdateRequest);
        this.invoiceRepository.save(invoice);

        logProducer.sendToLog(prepareLogRequest(OperationType.PUT,Messages.INVOICE_UPDATED));

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice),
                Messages.INVOICE_UPDATED
        );
    }

    @Override
    public Result deleteById(Long invoiceId) {
        Invoice invoice = this.invoiceRepository
                .findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND));

        this.invoiceRepository.deleteById(invoice.getId());

        logProducer.sendToLog(prepareLogRequest(OperationType.DELETE,Messages.INVOICE_DELETED));

        return new SuccessResult(Messages.INVOICE_DELETED);
    }

    @Override
    public DataResult<InvoiceDTO> findById(Long invoiceId) {
        InvoiceDTO invoiceDTO = this.invoiceRepository
                .findById(invoiceId)
                .map(InvoiceMapper.INSTANCE::convertToInvoiceDTO)
                .orElseThrow(() -> new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND));

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.INVOICE_FOUND));

        return new SuccessDataResult<>(
                invoiceDTO,
                Messages.INVOICE_FOUND
        );
    }

    @Override
    public DataResult<List<InvoiceDTO>> findAll() {
        List<Invoice> invoiceList = this.invoiceRepository.findAll();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.INVOICES_LISTED));

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertInvoiceDTOs(invoiceList),
                Messages.INVOICES_LISTED
        );
    }

    @Override
    public DataResult<UserResponse> findInvoiceUserByUserId(Long userId) {
        ResponseEntity<RestResponse<UserResponse>> response = userServiceClient.findById(userId);

        UserResponse userResponse = Objects.requireNonNull(response.getBody()).getData();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.INVOICE_USER_FOUND));

        return new SuccessDataResult<>(
                userResponse,
                Messages.INVOICE_USER_FOUND
        );
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message
    )
    {
        return LogRequest
                .builder()
                .serviceName("invoice-service")
                .operationType(operationType)
                .logType(LogType.INFO)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(null)
                .build();
    }
}
