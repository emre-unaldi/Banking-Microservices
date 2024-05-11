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
public record AccountUpdateRequest(
        Long id,
        String accountNumber,
        Long userId,
        Double balance,
        AccountType accountType,
        AccountStatus accountStatus,
        Long bankId
) {
}
