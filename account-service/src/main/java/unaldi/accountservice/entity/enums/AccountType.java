package unaldi.accountservice.entity.enums;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public enum AccountType {
    CURRENT("Current"),
    DEPOSIT("Deposit"),
    CREDIT("Credit");

    private final String message;

    AccountType(String message) {
        this.message = message;
    }
}
