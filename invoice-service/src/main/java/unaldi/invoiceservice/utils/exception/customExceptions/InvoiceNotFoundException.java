package unaldi.invoiceservice.utils.exception.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
