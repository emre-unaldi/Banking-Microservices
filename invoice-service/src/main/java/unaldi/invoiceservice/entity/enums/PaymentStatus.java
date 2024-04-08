package unaldi.invoiceservice.entity.enums;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public enum PaymentStatus {
    PAID("Paid"),
    PENDING("Pending"),
    OVERDUE("Overdue"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    private final String message;

    PaymentStatus(String message) {
        this.message = message;
    }
}
