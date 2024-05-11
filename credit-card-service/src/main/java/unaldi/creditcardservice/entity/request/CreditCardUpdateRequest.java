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
public record CreditCardUpdateRequest(
        Long id,
        String cardNumber,
        Long userId,
        LocalDate expirationDate,
        String cvv,
        Double creditLimit,
        Double debtAmount,
        Long bankId
) {
}
