package unaldi.invoiceservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.repository.InvoiceRepository;
import unaldi.invoiceservice.service.abstracts.InvoiceService;
import unaldi.invoiceservice.service.abstracts.mapper.InvoiceMapper;
import unaldi.invoiceservice.utils.constant.ExceptionMessages;
import unaldi.invoiceservice.utils.constant.Messages;
import unaldi.invoiceservice.utils.exception.customExceptions.InvoiceNotFoundException;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.Result;
import unaldi.invoiceservice.utils.result.SuccessDataResult;
import unaldi.invoiceservice.utils.result.SuccessResult;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public DataResult<InvoiceDTO> save(InvoiceSaveRequest invoiceSaveRequest) {
        Invoice invoice = InvoiceMapper.INSTANCE.convertToSaveInvoice(invoiceSaveRequest);
        this.invoiceRepository.save(invoice);

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice),
                Messages.INVOICE_CREATED
        );
    }

    @Override
    public DataResult<InvoiceDTO> update(InvoiceUpdateRequest invoiceUpdateRequest) {
        if(!this.invoiceRepository.existsById(invoiceUpdateRequest.id()))
            throw new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND);

        Invoice invoice = InvoiceMapper.INSTANCE.convertToUpdateInvoice(invoiceUpdateRequest);
        this.invoiceRepository.save(invoice);

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

        return new SuccessResult(Messages.INVOICE_DELETED);
    }

    @Override
    public DataResult<InvoiceDTO> findById(Long invoiceId) {
        InvoiceDTO invoiceDTO = this.invoiceRepository
                .findById(invoiceId)
                .map(InvoiceMapper.INSTANCE::convertToInvoiceDTO)
                .orElseThrow(() -> new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND));

        return new SuccessDataResult<>(
                invoiceDTO,
                Messages.INVOICE_FOUND
        );
    }

    @Override
    public DataResult<List<InvoiceDTO>> findAll() {
        List<Invoice> invoiceList = this.invoiceRepository.findAll();

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertInvoiceDTOs(invoiceList),
                Messages.INVOICES_LISTED
        );
    }
}
