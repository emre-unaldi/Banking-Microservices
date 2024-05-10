package unaldi.invoiceservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.enums.PaymentStatus;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.service.abstracts.mapper.InvoiceMapper;
import unaldi.invoiceservice.utils.ObjectFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@SpringBootTest
class MapperTest {
    @Test
    void should_convertSaveRequestToInvoice() {
        InvoiceSaveRequest invoiceSaveRequest = ObjectFactory.getInstance().getInvoiceSaveRequest();
        Invoice invoice = InvoiceMapper.INSTANCE.convertToSaveInvoice(invoiceSaveRequest);

        assertAll(
                () -> assertEquals(invoiceSaveRequest.invoiceNumber(), invoice.getInvoiceNumber()),
                () -> assertEquals(invoiceSaveRequest.userId(), invoice.getUserId()),
                () -> assertEquals(invoiceSaveRequest.amount(), invoice.getAmount()),
                () -> assertEquals(invoiceSaveRequest.invoiceDate(), invoice.getInvoiceDate()),
                () -> assertEquals(invoiceSaveRequest.dueDate(), invoice.getDueDate()),
                () -> assertEquals(invoiceSaveRequest.paymentStatus(), invoice.getPaymentStatus())
        );
    }

    @Test
    void should_convertUpdateRequestToInvoice() {
        InvoiceUpdateRequest invoiceUpdateRequest = ObjectFactory.getInstance().getInvoiceUpdateRequest();
        Invoice invoice = InvoiceMapper.INSTANCE.convertToUpdateInvoice(invoiceUpdateRequest);

        assertAll(
                () -> assertEquals(invoiceUpdateRequest.id(), invoice.getId()),
                () -> assertEquals(invoiceUpdateRequest.invoiceNumber(), invoice.getInvoiceNumber()),
                () -> assertEquals(invoiceUpdateRequest.userId(), invoice.getUserId()),
                () -> assertEquals(invoiceUpdateRequest.amount(), invoice.getAmount()),
                () -> assertEquals(invoiceUpdateRequest.invoiceDate(), invoice.getInvoiceDate()),
                () -> assertEquals(invoiceUpdateRequest.dueDate(), invoice.getDueDate()),
                () -> assertEquals(invoiceUpdateRequest.paymentStatus(), invoice.getPaymentStatus())
        );
    }

    @Test
    void should_convertInvoiceToInvoiceDTO() {
        Invoice invoice = ObjectFactory.getInstance().getInvoice();
        InvoiceDTO invoiceDTO = InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice);

        assertAll(
                () -> assertEquals(invoiceDTO.invoiceNumber(), invoice.getInvoiceNumber()),
                () -> assertEquals(invoiceDTO.userId(), invoice.getUserId()),
                () -> assertEquals(invoiceDTO.amount(), invoice.getAmount()),
                () -> assertEquals(invoiceDTO.invoiceDate(), invoice.getInvoiceDate()),
                () -> assertEquals(invoiceDTO.dueDate(), invoice.getDueDate()),
                () -> assertEquals(invoiceDTO.paymentStatus(), invoice.getPaymentStatus())
        );
    }

    @Test
    void should_convertListOfInvoicesToListOfInvoiceDTOs() {
        List<Invoice> invoiceList = ObjectFactory.getInstance().getInvoiceList();
        List<InvoiceDTO> invoiceDTOList = InvoiceMapper.INSTANCE.convertInvoiceDTOs(invoiceList);

        assertEquals(invoiceList.get(0).getId(), invoiceDTOList.get(0).id());
        assertEquals(invoiceList.get(1).getId(), invoiceDTOList.get(1).id());
    }
}
