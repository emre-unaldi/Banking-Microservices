package unaldi.invoiceservice.utils.constant;

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
    public static final String INVOICE_DELETED = "Invoice deleted from database";
    public static final String INVOICE_CREATED = "Invoice created in database";
    public static final String INVOICE_UPDATED = "Invoice updated in database";
    public static final String INVOICE_FOUND = "Invoice found in database";
    public static final String INVOICES_LISTED = "Invoices in the database are listed";
    public static final String INVOICE_USER_FOUND = "Invoice user found by user id";
    public static final String LOG_WRITE_QUEUE = "Log record written to queue";
}
