package unaldi.accountservice.utils.client.dto;

import lombok.Builder;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
public record BankResponse(
        Long id,
        String bankName,
        String bankCode,
        String branchName,
        String branchCode,
        String accountNumber,
        String address,
        String email,
        String phoneNumber
) {
}
