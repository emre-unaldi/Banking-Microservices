package unaldi.creditcardservice.entity.request;

import lombok.Builder;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
public record CreditCardSaveRequest(
        String cardNumber,
        Long userId,
        LocalDate expirationDate,
        String cvv,
        Double creditLimit,
        Double debtAmount,
        Long bankId
) {
}
