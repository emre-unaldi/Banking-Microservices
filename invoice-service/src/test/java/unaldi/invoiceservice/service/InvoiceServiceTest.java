package unaldi.invoiceservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.repository.InvoiceRepository;
import unaldi.invoiceservice.service.concretes.InvoiceServiceImpl;
import unaldi.invoiceservice.utils.FailTestMessages;
import unaldi.invoiceservice.utils.ObjectFactory;
import unaldi.invoiceservice.utils.client.UserServiceClient;
import unaldi.invoiceservice.utils.client.dto.RestResponse;
import unaldi.invoiceservice.utils.client.dto.UserResponse;
import unaldi.invoiceservice.utils.exception.customExceptions.InvoiceNotFoundException;
import unaldi.invoiceservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.invoiceservice.utils.rabbitMQ.request.LogRequest;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.Result;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {
    private static Invoice invoice;
    private static List<Invoice> invoiceList;
    private static InvoiceSaveRequest invoiceSaveRequest;
    private static InvoiceUpdateRequest invoiceUpdateRequest;
    private static UserResponse userResponse;
    private static RestResponse<UserResponse> userRestResponse;
    private final Long nonExistentInvoicedId = -1L;

    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private LogProducer logProducer;
    @Mock
    private UserServiceClient userServiceClient;
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeAll
    static void setUp() {
        invoice = ObjectFactory.getInstance().getInvoice();
        invoiceList = ObjectFactory.getInstance().getInvoiceList();
        invoiceSaveRequest = ObjectFactory.getInstance().getInvoiceSaveRequest();
        invoiceUpdateRequest = ObjectFactory.getInstance().getInvoiceUpdateRequest();
        userResponse = ObjectFactory.getInstance().getUserResponse();
        userRestResponse = ObjectFactory.getInstance().getRestResponse(userResponse);
    }

    @Test
    void givenInvoiceSaveRequest_whenSave_thenInvoiceShouldBeSaved() {
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        DataResult<InvoiceDTO> result = invoiceService.save(invoiceSaveRequest);
        assertTrue(result.getSuccess(), FailTestMessages.INVOICE_SAVE);

        verify(invoiceRepository, times(1)).save(any(Invoice.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenInvoiceUpdateRequest_whenUpdate_thenInvoiceShouldBeUpdated() {
        when(invoiceRepository.existsById(invoiceUpdateRequest.id())).thenReturn(true);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        DataResult<InvoiceDTO> result = invoiceService.update(invoiceUpdateRequest);
        assertTrue(result.getSuccess(), FailTestMessages.INVOICE_UPDATE);

        verify(invoiceRepository, times(1)).existsById(anyLong());
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenInvoiceId_whenDeleteById_thenInvoiceShouldBeDeleted() {
        when(invoiceRepository.findById(invoice.getId())).thenReturn(Optional.of(invoice));

        Result result = invoiceService.deleteById(invoice.getId());
        assertTrue(result.getSuccess(), FailTestMessages.INVOICE_DELETE);

        verify(invoiceRepository, times(1)).deleteById(invoice.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenInvoiceId_whenFindById_thenInvoiceShouldBeFound() {
        when(invoiceRepository.findById(invoice.getId())).thenReturn(Optional.of(invoice));

        DataResult<InvoiceDTO> result = invoiceService.findById(invoice.getId());
        assertTrue(result.getSuccess(), FailTestMessages.INVOICE_FIND);

        verify(invoiceRepository, times(1)).findById(invoice.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserResponse_whenFindInvoiceUserByUserId_thenInvoiceUserShouldBeFound() {
        when(userServiceClient.findById(userResponse.id())).thenReturn(ResponseEntity.ok(userRestResponse));

        DataResult<UserResponse> result = invoiceService.findInvoiceUserByUserId(userResponse.id());
        assertTrue(result.getSuccess(), FailTestMessages.INVOICE_USER_FIND);

        verify(userServiceClient, times(1)).findById(userResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenInvoiceList_whenFindAll_thenAllInvoicesShouldBeReturned() {
        when(invoiceRepository.findAll()).thenReturn(invoiceList);

        DataResult<List<InvoiceDTO>> result = invoiceService.findAll();
        assertTrue(result.getSuccess(), FailTestMessages.INVOICES_FIND);

        verify(invoiceRepository, times(1)).findAll();
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentInvoicedUpdateRequest_whenUpdate_thenInvoiceNotFoundExceptionShouldBeThrown() {
        when(invoiceRepository.existsById(invoiceUpdateRequest.id())).thenReturn(false);

        assertThrows(InvoiceNotFoundException.class, () -> {
            invoiceService.update(invoiceUpdateRequest);
        }, FailTestMessages.INVOICE_UPDATE_EXCEPTION);

        verify(invoiceRepository, times(1)).existsById(anyLong());
        verify(invoiceRepository, never()).save(any(Invoice.class));
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentInvoiceId_whenDeleteById_thenInvoiceNotFoundExceptionShouldBeThrown() {
        when(invoiceRepository.findById(nonExistentInvoicedId)).thenReturn(Optional.empty());

        assertThrows(InvoiceNotFoundException.class, () -> {
            invoiceService.deleteById(nonExistentInvoicedId);
        }, FailTestMessages.INVOICE_DELETE_EXCEPTION);

        verify(invoiceRepository, times(1)).findById(nonExistentInvoicedId);
        verify(invoiceRepository, never()).deleteById(anyLong());
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentInvoiceId_whenFindById_thenInvoiceNotFoundExceptionShouldBeThrown() {
        when(invoiceRepository.findById(nonExistentInvoicedId)).thenReturn(Optional.empty());

        assertThrows(InvoiceNotFoundException.class, () -> {
            invoiceService.findById(nonExistentInvoicedId);
        }, FailTestMessages.INVOICE_FIND_EXCEPTION);

        verify(invoiceRepository, times(1)).findById(nonExistentInvoicedId);
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }
}
