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
public class ExceptionMessages {
    public static final String INVOICE_NOT_FOUND = "Invoice not found in database";
    public static final String BAD_REQUEST = "The request could not be fulfilled";
}
