package unaldi.invoiceservice.entity.request;

import lombok.Builder;
import unaldi.invoiceservice.entity.enums.PaymentStatus;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
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
