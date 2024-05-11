package unaldi.invoiceservice.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.service.abstracts.mapper.InvoiceMapper;
import unaldi.invoiceservice.utils.FailTestMessages;
import unaldi.invoiceservice.utils.ObjectFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
class InvoiceMapperTest {
    private static Invoice invoice;
    private static List<Invoice> invoiceList;
    private static InvoiceSaveRequest invoiceSaveRequest;
    private static InvoiceUpdateRequest invoiceUpdateRequest;

    @BeforeAll
    static void setUp() {
        invoice = ObjectFactory.getInstance().getInvoice();
        invoiceList = ObjectFactory.getInstance().getInvoiceList();
        invoiceSaveRequest = ObjectFactory.getInstance().getInvoiceSaveRequest();
        invoiceUpdateRequest = ObjectFactory.getInstance().getInvoiceUpdateRequest();
    }

    @Test
    void should_convertSaveRequestToInvoice() {
        Invoice invoice = InvoiceMapper.INSTANCE.convertToSaveInvoice(invoiceSaveRequest);

        assertAll(
                () -> assertEquals(invoiceSaveRequest.invoiceNumber(), invoice.getInvoiceNumber(), FailTestMessages.INVOICE_NUMBER_MISMATCH),
                () -> assertEquals(invoiceSaveRequest.userId(), invoice.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(invoiceSaveRequest.amount(), invoice.getAmount(), FailTestMessages.AMOUNT_MISMATCH),
                () -> assertEquals(invoiceSaveRequest.invoiceDate(), invoice.getInvoiceDate(), FailTestMessages.INVOICE_DATE_MISMATCH),
                () -> assertEquals(invoiceSaveRequest.dueDate(), invoice.getDueDate(), FailTestMessages.DUE_DATE_MISMATCH),
                () -> assertEquals(invoiceSaveRequest.paymentStatus(), invoice.getPaymentStatus(), FailTestMessages.PAYMENT_STATUS_MISMATCH)
        );
    }

    @Test
    void should_convertUpdateRequestToInvoice() {
        Invoice invoice = InvoiceMapper.INSTANCE.convertToUpdateInvoice(invoiceUpdateRequest);

        assertAll(
                () -> assertEquals(invoiceUpdateRequest.invoiceNumber(), invoice.getInvoiceNumber(), FailTestMessages.INVOICE_NUMBER_MISMATCH),
                () -> assertEquals(invoiceUpdateRequest.userId(), invoice.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(invoiceUpdateRequest.amount(), invoice.getAmount(), FailTestMessages.AMOUNT_MISMATCH),
                () -> assertEquals(invoiceUpdateRequest.invoiceDate(), invoice.getInvoiceDate(), FailTestMessages.INVOICE_DATE_MISMATCH),
                () -> assertEquals(invoiceUpdateRequest.dueDate(), invoice.getDueDate(), FailTestMessages.DUE_DATE_MISMATCH),
                () -> assertEquals(invoiceUpdateRequest.paymentStatus(), invoice.getPaymentStatus(), FailTestMessages.PAYMENT_STATUS_MISMATCH)
        );
    }

    @Test
    void should_convertInvoiceToInvoiceDTO() {
        InvoiceDTO invoiceDTO = InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice);

        assertAll(
                () -> assertEquals(invoiceDTO.invoiceNumber(), invoice.getInvoiceNumber(), FailTestMessages.INVOICE_NUMBER_MISMATCH),
                () -> assertEquals(invoiceDTO.userId(), invoice.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(invoiceDTO.amount(), invoice.getAmount(), FailTestMessages.AMOUNT_MISMATCH),
                () -> assertEquals(invoiceDTO.invoiceDate(), invoice.getInvoiceDate(), FailTestMessages.INVOICE_DATE_MISMATCH),
                () -> assertEquals(invoiceDTO.dueDate(), invoice.getDueDate(), FailTestMessages.DUE_DATE_MISMATCH),
                () -> assertEquals(invoiceDTO.paymentStatus(), invoice.getPaymentStatus(), FailTestMessages.PAYMENT_STATUS_MISMATCH)
        );
    }

    @Test
    void should_convertListOfInvoicesToListOfInvoiceDTOs() {
        List<InvoiceDTO> invoiceDTOList = InvoiceMapper.INSTANCE.convertInvoiceDTOs(invoiceList);

        assertEquals(invoiceList.get(0).getId(), invoiceDTOList.get(0).id());
        assertEquals(invoiceList.get(1).getId(), invoiceDTOList.get(1).id());
    }
}
