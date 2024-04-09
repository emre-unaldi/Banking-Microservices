package unaldi.logservice.utils.exception.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LogNotFoundException extends RuntimeException{
    public LogNotFoundException(String message) {
        super(message);
    }
}
