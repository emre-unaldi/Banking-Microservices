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
public class Caches {
    public static final String BANK_CACHE = "bank";
    public static final String BANKS_CACHE = "banks";
    public static final String KEY_PREFIX = "bank-service:";
}
