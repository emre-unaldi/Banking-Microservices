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
public class Caches {
    public static final String CREDIT_CARD_CACHE = "credit_card";
    public static final String CREDIT_CARDS_CACHE = "credit_cards";
    public static final String CREDIT_CARD_USER_CACHE = "credit_card_user";
    public static final String CREDIT_CARD_BANK_CACHE = "credit_card_bank";
    public static final String KEY_PREFIX = "credit-card-service:";
}
