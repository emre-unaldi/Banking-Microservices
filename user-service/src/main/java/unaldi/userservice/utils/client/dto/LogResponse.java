package unaldi.userservice.utils.client.dto;

import unaldi.userservice.utils.client.enums.LogType;
import unaldi.userservice.utils.client.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record LogResponse(
        String serviceName,
        OperationType operationType,
        LogType logType,
        String message,
        LocalDateTime timestamp,
        String exception
) {
}
