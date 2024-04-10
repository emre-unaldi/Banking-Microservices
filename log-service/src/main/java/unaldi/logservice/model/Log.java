package unaldi.logservice.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import unaldi.logservice.model.enums.LogType;
import unaldi.logservice.model.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Document(collection = "logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    private String id;

    @Field(name = "service_name")
    private String serviceName;

    @Enumerated(EnumType.STRING)
    @Field("operation_type")
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    @Field("log_type")
    private LogType logType;

    @Field(name = "message")
    private String message;

    @Field(name = "timestamp")
    private LocalDateTime timestamp;

    @Field(name = "exception")
    private String exception;
}