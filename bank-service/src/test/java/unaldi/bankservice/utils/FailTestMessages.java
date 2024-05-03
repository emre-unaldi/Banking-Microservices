package unaldi.bankservice.utils;

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
    public static final String BANK_SAVE = "Bank save operation test failed !";
    public static final String BANK_UPDATE = "Bank update operation test failed !";
    public static final String BANK_DELETE = "Bank delete operation test failed !";
    public static final String BANK_FIND = "Bank find operation test failed !";
    public static final String BANKS_FIND = "Bank list fetch operation test failed !";

    public static final String BANK_UPDATE_EXCEPTION = "BankNotFoundException was expected but not thrown during update operation";
    public static final String BANK_DELETE_EXCEPTION = "BankNotFoundException was expected but not thrown during delete operation";
    public static final String BANK_FIND_EXCEPTION = "BankNotFoundException was expected but not thrown during find operation";

    public static final String BANK_NAME_MISMATCH = "Bank name does not match";
    public static final String BANK_CODE_MISMATCH = "Bank code does not match";
    public static final String BRANCH_NAME_MISMATCH = "Branch name does not match";
    public static final String BRANCH_CODE_MISMATCH = "Branch code name does not match";
    public static final String ACCOUNT_NUMBER_MISMATCH = "Account number does not match";
    public static final String ADDRESS_MISMATCH = "Address does not match";
    public static final String EMAIL_MISMATCH = "Email does not match";
    public static final String PHONE_NUMBER_MISMATCH = "Phone number does not match";
}