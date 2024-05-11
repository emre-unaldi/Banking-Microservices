package unaldi.accountservice.entity.request;

import lombok.Builder;
import unaldi.accountservice.entity.enums.AccountStatus;
import unaldi.accountservice.entity.enums.AccountType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
public record AccountSaveRequest(
        String accountNumber,
        Long userId,
        Double balance,
        AccountType accountType,
        AccountStatus accountStatus,
        Long bankId
) {
}
