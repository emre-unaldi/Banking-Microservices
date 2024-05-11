package unaldi.bankservice.entity.request;

import lombok.Builder;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Builder
public record BankSaveRequest(
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
