package unaldi.creditcardservice.utils.exception.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private String httpMethod;
    private String errorType;
    private String requestPath;
}
