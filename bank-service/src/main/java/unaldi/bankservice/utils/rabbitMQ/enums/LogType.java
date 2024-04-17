package unaldi.bankservice.utils.rabbitMQ.enums;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public enum LogType {
    ERROR("ERROR"),
    WARN("WARN"),
    INFO("INFO");

    private final String message;

    LogType(String message) {
        this.message = message;
    }
}
