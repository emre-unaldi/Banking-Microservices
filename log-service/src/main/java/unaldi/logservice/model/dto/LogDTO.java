package unaldi.logservice.model.dto;

import unaldi.logservice.model.enums.LogType;
import unaldi.logservice.model.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record LogDTO(
        String id,
        String serviceName,
        OperationType operationType,
        LogType logType,
        String message,
        LocalDateTime timestamp,
        String exception
) {
    @Override
    public String toString() {
        return String.format(
                "LogDTO{id='%s', serviceName='%s', operationType='%s', logType='%s', message='%s', timestamp='%s', exception='%s'}",
                id, serviceName, operationType, logType, message, timestamp, exception);
    }
}
