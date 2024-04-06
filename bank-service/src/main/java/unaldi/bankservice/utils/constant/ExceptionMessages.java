package unaldi.bankservice.utils.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
    public static final String BANK_NOT_FOUND = "Bank not found in database";
    public static final String BAD_REQUEST = "The request could not be fulfilled";
}
