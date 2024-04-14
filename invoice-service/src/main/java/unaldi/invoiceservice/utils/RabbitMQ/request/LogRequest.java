package unaldi.invoiceservice.utils.RabbitMQ.request;

import lombok.*;
import unaldi.invoiceservice.utils.RabbitMQ.enums.LogType;
import unaldi.invoiceservice.utils.RabbitMQ.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LogRequest {
    private String serviceName;
    private OperationType operationType;
    private LogType logType;
    private String message;
    private LocalDateTime timestamp;
    private String exception;
}
