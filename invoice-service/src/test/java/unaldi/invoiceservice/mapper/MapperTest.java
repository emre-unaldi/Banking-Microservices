package unaldi.invoiceservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.dto.InvoiceDTO;
import unaldi.invoiceservice.entity.enums.PaymentStatus;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.service.abstracts.mapper.InvoiceMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@SpringBootTest
public class MapperTest {
    @Test
    void should_convertSaveRequestToInvoice() {
        InvoiceSaveRequest invoiceSaveRequest = new InvoiceSaveRequest(
                "89457423895",
                1L,
                3250.50,
                LocalDate.of(2024,4,7),
                LocalDate.of(2024,5,20),
                PaymentStatus.PENDING
        );

        Invoice invoice = InvoiceMapper.INSTANCE.convertToSaveInvoice(invoiceSaveRequest);

        assertEquals(invoiceSaveRequest.invoiceNumber(), invoice.getInvoiceNumber());
        assertEquals(invoiceSaveRequest.userId(), invoice.getUserId());
        assertEquals(invoiceSaveRequest.amount(), invoice.getAmount());
        assertEquals(invoiceSaveRequest.invoiceDate(), invoice.getInvoiceDate());
        assertEquals(invoiceSaveRequest.dueDate(), invoice.getDueDate());
        assertEquals(invoiceSaveRequest.paymentStatus(), invoice.getPaymentStatus());
    }

    @Test
    void should_convertUpdateRequestToInvoice() {
        InvoiceUpdateRequest invoiceUpdateRequest = new InvoiceUpdateRequest(
                1L,
                "89457423895",
                1L,
                3250.50,
                LocalDate.of(2024,4,7),
                LocalDate.of(2024,5,20),
                PaymentStatus.PENDING
        );

        Invoice invoice = InvoiceMapper.INSTANCE.convertToUpdateInvoice(invoiceUpdateRequest);

        assertEquals(invoiceUpdateRequest.id(), invoice.getId());
        assertEquals(invoiceUpdateRequest.invoiceNumber(), invoice.getInvoiceNumber());
        assertEquals(invoiceUpdateRequest.userId(), invoice.getUserId());
        assertEquals(invoiceUpdateRequest.amount(), invoice.getAmount());
        assertEquals(invoiceUpdateRequest.invoiceDate(), invoice.getInvoiceDate());
        assertEquals(invoiceUpdateRequest.dueDate(), invoice.getDueDate());
        assertEquals(invoiceUpdateRequest.paymentStatus(), invoice.getPaymentStatus());
    }

    @Test
    void should_convertInvoiceToInvoiceDTO() {
        Invoice invoice = new Invoice(
                1L,
                "89457423895",
                1L,
                3250.50,
                LocalDate.of(2024,4,7),
                LocalDate.of(2024,5,20),
                PaymentStatus.PENDING
        );

        InvoiceDTO invoiceDTO = InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice);

        assertEquals(invoiceDTO.id(), invoice.getId());
        assertEquals(invoiceDTO.invoiceNumber(), invoice.getInvoiceNumber());
        assertEquals(invoiceDTO.userId(), invoice.getUserId());
        assertEquals(invoiceDTO.amount(), invoice.getAmount());
        assertEquals(invoiceDTO.invoiceDate(), invoice.getInvoiceDate());
        assertEquals(invoiceDTO.dueDate(), invoice.getDueDate());
        assertEquals(invoiceDTO.paymentStatus(), invoice.getPaymentStatus());
    }

    @Test
    void should_convertListOfInvoicesToListOfInvoiceDTOs() {
        List<Invoice> invoiceList = new ArrayList<>(Arrays.asList(
                new Invoice(
                        2L,
                        "89457423895",
                        1L,
                        3250.50,
                        LocalDate.of(2024,4,7),
                        LocalDate.of(2024,5,20),
                        PaymentStatus.PENDING
                ),
                new Invoice(
                        2L,
                        "89457423895",
                        1L,
                        3250.50,
                        LocalDate.of(2024,4,7),
                        LocalDate.of(2024,5,20),
                        PaymentStatus.PENDING
                )
        ));

        List<InvoiceDTO> invoiceDTOList = InvoiceMapper.INSTANCE.convertInvoiceDTOs(invoiceList);

        assertEquals(invoiceList.get(0).getId(), invoiceDTOList.get(0).id());
        assertEquals(invoiceList.get(1).getId(), invoiceDTOList.get(1).id());
    }
}
