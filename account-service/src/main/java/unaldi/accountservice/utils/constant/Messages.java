package unaldi.accountservice.utils.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Messages {
    public static final String ACCOUNT_DELETED = "Account deleted from database";
    public static final String ACCOUNT_CREATED = "Account created in database";
    public static final String ACCOUNT_UPDATED = "Account updated in database";
    public static final String ACCOUNT_FOUND = "Account found in database";
    public static final String ACCOUNTS_LISTED = "Accounts in the database are listed";
    public static final String ACCOUNT_USER_FOUND = "Account user found by user id";
    public static final String ACCOUNT_BANK_FOUND = "Account bank found by bank id";
    public static final String LOG_WRITE_QUEUE = "Log record written to queue";
}
