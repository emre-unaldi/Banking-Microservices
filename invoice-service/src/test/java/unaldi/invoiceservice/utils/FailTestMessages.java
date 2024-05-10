package unaldi.invoiceservice.utils;

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
    public static final String INVOICE_SAVE = "Invoice save operation test failed !";
    public static final String INVOICE_UPDATE = "Invoice update operation test failed !";
    public static final String INVOICE_DELETE = "Invoice delete operation test failed !";
    public static final String INVOICE_FIND = "Invoice find operation test failed !";
    public static final String INVOICES_FIND = "Invoice list fetch operation test failed !";
    public static final String INVOICE_USER_FIND = "Invoice user find operation test failed !";

    public static final String INVOICE_UPDATE_EXCEPTION = "InvoiceNotFoundException was expected but not thrown during update operation";
    public static final String INVOICE_DELETE_EXCEPTION = "InvoiceNotFoundException was expected but not thrown during delete operation";
    public static final String INVOICE_FIND_EXCEPTION = "InvoiceNotFoundException was expected but not thrown during find operation";

    public static final String INVOICE_NUMBER_MISMATCH = "Invoice number does not match";
    public static final String USER_ID_MISMATCH = "User id does not match";
    public static final String AMOUNT_MISMATCH = "Amount does not match";
    public static final String INVOICE_DATE_MISMATCH = "Invoice date does not match";
    public static final String DUE_DATE_MISMATCH = "Due date does not match";
    public static final String PAYMENT_STATUS_MISMATCH = "Payment status does not match";
}
