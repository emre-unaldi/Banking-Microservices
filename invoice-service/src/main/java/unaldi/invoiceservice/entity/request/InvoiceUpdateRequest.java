package unaldi.invoiceservice.entity.request;

import unaldi.invoiceservice.entity.enums.PaymentStatus;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record InvoiceUpdateRequest(
        Long id,
        String invoiceNumber,
        Long userId,
        Double amount,
        LocalDate invoiceDate,
        LocalDate dueDate,
        PaymentStatus paymentStatus
) {
}
