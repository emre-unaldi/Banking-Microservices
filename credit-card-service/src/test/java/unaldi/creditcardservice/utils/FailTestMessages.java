package unaldi.creditcardservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FailTestMessages {
    public static final String CREDIT_CARD_SAVE = "Credit card save operation test failed !";
    public static final String CREDIT_CARD_UPDATE = "Credit card update operation test failed !";
    public static final String CREDIT_CARD_DELETE = "Credit card delete operation test failed !";
    public static final String CREDIT_CARD_FIND = "Credit card find operation test failed !";
    public static final String CREDIT_CARDS_FIND = "Credit card list fetch operation test failed !";
    public static final String CREDIT_CARD_USER_FIND = "Credit card user find operation test failed !";
    public static final String CREDIT_CARD_BANK_FIND = "Credit card bank find operation test failed !";


    public static final String CREDIT_CARD_UPDATE_EXCEPTION = "CreditCardNotFoundException was expected but not thrown during update operation";
    public static final String CREDIT_CARD_DELETE_EXCEPTION = "CreditCardNotFoundException was expected but not thrown during delete operation";
    public static final String CREDIT_CARD_FIND_EXCEPTION = "CreditCardNotFoundException was expected but not thrown during find operation";

    public static final String CARD_NUMBER_MISMATCH = "Card number does not match";
    public static final String USER_ID_MISMATCH = "User id does not match";
    public static final String EXPIRATION_DATE_MISMATCH = "Expiration date does not match";
    public static final String CVV_MISMATCH = "Cvv does not match";
    public static final String CREDIT_LIMIT_MISMATCH = "Credit limit does not match";
    public static final String DEBT_AMOUNT_MISMATCH = "Debt amount does not match";
    public static final String BANK_ID_MISMATCH = "Bank id does not match";
}
