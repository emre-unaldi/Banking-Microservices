package unaldi.bankservice.entity.request;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record BankUpdateRequest(
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
