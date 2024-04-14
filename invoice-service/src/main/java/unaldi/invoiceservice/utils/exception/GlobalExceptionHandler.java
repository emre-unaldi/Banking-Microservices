package unaldi.invoiceservice.utils.exception;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import unaldi.invoiceservice.utils.RabbitMQ.enums.LogType;
import unaldi.invoiceservice.utils.RabbitMQ.enums.OperationType;
import unaldi.invoiceservice.utils.RabbitMQ.producer.LogProducer;
import unaldi.invoiceservice.utils.RabbitMQ.request.LogRequest;
import unaldi.invoiceservice.utils.constant.ExceptionMessages;
import unaldi.invoiceservice.utils.exception.customExceptions.InvoiceNotFoundException;
import unaldi.invoiceservice.utils.exception.dto.ExceptionResponse;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.ErrorDataResult;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final LogProducer logProducer;

    @Autowired
    public GlobalExceptionHandler(LogProducer logProducer) {
        this.logProducer = logProducer;
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleCreditCardNotFoundException(InvoiceNotFoundException exception, WebRequest request) {
        log.error("CreditCardNotFoundException occurred : {0}", exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.NOT_FOUND, request),
                        ExceptionMessages.INVOICE_NOT_FOUND)
                );
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleFeignNotFoundException(FeignException.NotFound exception, WebRequest request) {
        log.error("Feign NotFoundException occurred : {0}", exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.NOT_FOUND, request),
                        ExceptionMessages.RESOURCE_NOT_FOUND)
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleAllException(Exception exception, WebRequest request) {
        log.error("Exception occurred : {0}", exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.BAD_REQUEST, request),
                        ExceptionMessages.BAD_REQUEST)
                );
    }

    private ExceptionResponse prepareExceptionResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
        HttpServletRequest servletRequest = ((NativeWebRequest) request).getNativeRequest(HttpServletRequest.class);

        String httpMethod = Optional.ofNullable(servletRequest).map(HttpServletRequest::getMethod).orElse("Unknown");
        String requestPath = Optional.ofNullable(servletRequest).map(HttpServletRequest::getRequestURI).orElse("Unknown");
        String exceptionMessage = httpStatus + " - " + exception.getClass().getSimpleName();

        logProducer.sendToLog(prepareLogRequest(OperationType.valueOf(httpMethod), exception.getMessage(), exceptionMessage));

        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .httpStatusCode(httpStatus.value())
                .httpMethod(httpMethod)
                .errorType(exception.getClass().getSimpleName())
                .requestPath(requestPath)
                .build();
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message,
            String exception
    )
    {
        return LogRequest
                .builder()
                .serviceName("invoice-service")
                .operationType(operationType)
                .logType(LogType.ERROR)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(exception)
                .build();
    }
}
