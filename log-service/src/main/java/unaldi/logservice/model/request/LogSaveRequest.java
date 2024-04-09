package unaldi.logservice.model.request;

import unaldi.logservice.model.enums.LogType;
import unaldi.logservice.model.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record LogSaveRequest (
         String serviceName,
         OperationType operationType,
         LogType logType,
         String message,
         String requestPath,
         LocalDateTime timestamp,
         String exception
){
}