package unaldi.accountservice.entity.enums;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public enum AccountStatus {
    ACTIVE("Active"),
    INACTIVE("In Active");

    private final String message;

    AccountStatus(String message) {
        this.message = message;
    }
}
