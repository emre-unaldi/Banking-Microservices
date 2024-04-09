package unaldi.logservice.model.dto;

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
public class LogDTO{
    private String id;
    private String serviceName;
    private String operationType;
    private String logType;
    private String message;
    private LocalDateTime timestamp;
    private String exception;
}