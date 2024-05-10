package unaldi.accountservice.utils;

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
    public static final String ACCOUNT_SAVE = "Account save operation test failed !";
    public static final String ACCOUNT_UPDATE = "Account update operation test failed !";
    public static final String ACCOUNT_DELETE = "Account delete operation test failed !";
    public static final String ACCOUNT_FIND = "Account find operation test failed !";
    public static final String ACCOUNTS_FIND = "Account list fetch operation test failed !";
    public static final String ACCOUNT_USER_FIND = "Account user find operation test failed !";
    public static final String ACCOUNT_BANK_FIND = "Account bank find operation test failed !";

    public static final String ACCOUNT_UPDATE_EXCEPTION = "AccountNotFoundException was expected but not thrown during update operation";
    public static final String ACCOUNT_DELETE_EXCEPTION = "AccountNotFoundException was expected but not thrown during delete operation";
    public static final String ACCOUNT_FIND_EXCEPTION = "AccountNotFoundException was expected but not thrown during find operation";

    public static final String ACCOUNT_NUMBER_MISMATCH = "Account number does not match";
    public static final String USER_ID_MISMATCH = "User id does not match";
    public static final String BALANCE_MISMATCH = "Balance does not match";
    public static final String ACCOUNT_TYPE_MISMATCH = "Account type does not match";
    public static final String ACCOUNT_STATUS_MISMATCH = "Account status does not match";
    public static final String BANK_ID_MISMATCH = "Bank id does not match";
}
