package unaldi.creditcardservice.utils.constant;

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
    public static final String CREDIT_CARD_NOT_FOUND = "Credit card not found in database";
    public static final String BAD_REQUEST = "The request could not be fulfilled";
    public static final String RESOURCE_NOT_FOUND = "The requested resource could not be found. This error occurred during a Feign client request";
}
