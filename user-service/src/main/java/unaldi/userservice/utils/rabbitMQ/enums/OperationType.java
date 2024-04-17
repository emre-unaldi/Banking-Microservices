package unaldi.userservice.utils.rabbitMQ.enums;

import lombok.Getter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
public enum OperationType {
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    GET("GET");

    private final String message;

    OperationType(String message) {
        this.message = message;
    }
}
