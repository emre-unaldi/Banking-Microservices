package unaldi.accountservice.entity.dto;

import unaldi.accountservice.entity.enums.AccountStatus;
import unaldi.accountservice.entity.enums.AccountType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record AccountDTO(
        Long id,
        String accountNumber,
        Long userId,
        Double balance,
        AccountType accountType,
        AccountStatus accountStatus,
        Long bankId
) {
}
