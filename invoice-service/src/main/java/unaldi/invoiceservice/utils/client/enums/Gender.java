package unaldi.invoiceservice.utils.client.enums;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String message;

    Gender(String message) {
        this.message = message;
    }
}
