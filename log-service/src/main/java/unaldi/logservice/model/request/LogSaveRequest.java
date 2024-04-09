package unaldi.logservice.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
@Setter
public class LogSaveRequest{
    private String serviceName;
    private String operationType;
    private String logType;
    private String message;
    private LocalDateTime timestamp;
    private String exception;
}
