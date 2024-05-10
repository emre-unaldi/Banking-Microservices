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
import java.util.LinkedList;
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
            invoice = new Invoice(
                    1L,
                    "89457423895",
                    1L,
                    3250.50,
                    LocalDate.of(2024,4,7),
                    LocalDate.of(2024,5,20),
                    PaymentStatus.PENDING
            );
        }

        return invoice;
    }

    public InvoiceUpdateRequest getInvoiceUpdateRequest() {
        if (invoiceUpdateRequest == null) {
            invoiceUpdateRequest = new InvoiceUpdateRequest(
                    1L,
                    "89457423895",
                    1L,
                    3250.50,
                    LocalDate.of(2024,4,7),
                    LocalDate.of(2024,5,20),
                    PaymentStatus.PENDING
            );
        }

        return invoiceUpdateRequest;
    }

    public InvoiceSaveRequest getInvoiceSaveRequest() {
        if (invoiceSaveRequest == null) {
            invoiceSaveRequest = new InvoiceSaveRequest(
                    "89457423895",
                    1L,
                    3250.50,
                    LocalDate.of(2024,4,7),
                    LocalDate.of(2024,5,20),
                    PaymentStatus.PENDING
            );
        }

        return invoiceSaveRequest;
    }

    public List<Invoice> getInvoiceList() {
        if (invoiceList == null) {
            invoiceList = List.of(
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
            );
        }

        return invoiceList;
    }

    public UserResponse getUserResponse() {
        if (userResponse == null) {
            userResponse = new UserResponse(
                    1L,
                    "eunaldi",
                    "121221",
                    "emree.unaldi@gmail.com",
                    "Emre",
                    "Ünaldı",
                    "05078711189",
                    LocalDate.of(2001, 1, 1),
                    Gender.MALE
            );
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
