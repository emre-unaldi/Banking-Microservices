package unaldi.logservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field("operation_type")
    private String operationType;

    @Field("log_type")
    private String logType;

    @Field(name = "message")
    private String message;

    @Field(name = "timestamp")
    private LocalDateTime timestamp;

    @Field(name = "exception")
    private String exception;
}
