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
public class Messages {
    public static final String BANK_DELETED = "Bank deleted from database";
    public static final String BANK_CREATED = "Bank created in database";
    public static final String BANK_UPDATED = "Bank updated in database";
    public static final String BANK_FOUND = "Bank found in database";
    public static final String BANKS_LISTED = "Banks in the database are listed";
    public static final String LOG_WRITE_QUEUE = "Log record written to queue";
}
