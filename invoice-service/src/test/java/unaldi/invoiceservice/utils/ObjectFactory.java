package unaldi.invoiceservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import unaldi.invoiceservice.entity.Invoice;
import unaldi.invoiceservice.entity.enums.PaymentStatus;
import unaldi.invoiceservice.entity.request.InvoiceSaveRequest;
import unaldi.invoiceservice.entity.request.InvoiceUpdateRequest;
import unaldi.invoiceservice.utils.client.dto.RestResponse;
import unaldi.invoiceservice.utils.client.dto.UserResponse;
import unaldi.invoiceservice.utils.client.enums.Gender;

import java.time.LocalDate;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectFactory {
    private static ObjectFactory instance;
    private Invoice invoice;
    private InvoiceUpdateRequest invoiceUpdateRequest;
    private InvoiceSaveRequest invoiceSaveRequest;
    private List<Invoice> invoiceList;
    private UserResponse userResponse;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public Invoice getInvoice() {
        if (invoice == null) {
            invoice = Invoice.builder()
                    .id(1L)
                    .invoiceNumber("89457423895")
                    .userId(1L)
                    .amount(3250.50)
                    .invoiceDate(LocalDate.of(2024, 4, 7))
                    .dueDate(LocalDate.of(2024, 5, 20))
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();
        }

        return invoice;
    }

    public InvoiceUpdateRequest getInvoiceUpdateRequest() {
        if (invoiceUpdateRequest == null) {
            invoiceUpdateRequest = InvoiceUpdateRequest.builder()
                    .id(1L)
                    .invoiceNumber("89457423895")
                    .userId(1L)
                    .amount(3250.50)
                    .invoiceDate(LocalDate.of(2024, 4, 7))
                    .dueDate(LocalDate.of(2024, 5, 20))
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();
        }

        return invoiceUpdateRequest;
    }

    public InvoiceSaveRequest getInvoiceSaveRequest() {
        if (invoiceSaveRequest == null) {
            invoiceSaveRequest = InvoiceSaveRequest.builder()
                    .invoiceNumber("89457423895")
                    .userId(1L)
                    .amount(3250.50)
                    .invoiceDate(LocalDate.of(2024, 4, 7))
                    .dueDate(LocalDate.of(2024, 5, 20))
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();
        }

        return invoiceSaveRequest;
    }

    public List<Invoice> getInvoiceList() {
        if (invoiceList == null) {
            Invoice invoiceOne = Invoice.builder()
                    .id(1L)
                    .invoiceNumber("89457423895")
                    .userId(1L)
                    .amount(3250.50)
                    .invoiceDate(LocalDate.of(2024, 4, 7))
                    .dueDate(LocalDate.of(2024, 5, 20))
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();

            Invoice invoiceTwo = Invoice.builder()
                    .id(1L)
                    .invoiceNumber("89457423895")
                    .userId(1L)
                    .amount(3250.50)
                    .invoiceDate(LocalDate.of(2024, 4, 7))
                    .dueDate(LocalDate.of(2024, 5, 20))
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();

            invoiceList = List.of(
                    invoiceOne,
                    invoiceTwo
            );
        }

        return invoiceList;
    }

    public UserResponse getUserResponse() {
        if (userResponse == null) {
            userResponse = UserResponse.builder()
                    .id(1L)
                    .username("eunaldi")
                    .password("121221")
                    .email("emree.unaldi@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();
        }

        return userResponse;
    }

    public <T> RestResponse<T> getRestResponse(T entityResponse) {
        return RestResponse.<T>builder()
                .data(entityResponse)
                .success(true)
                .build();
    }
}
