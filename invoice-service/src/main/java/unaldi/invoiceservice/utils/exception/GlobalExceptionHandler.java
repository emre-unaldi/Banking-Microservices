package unaldi.invoiceservice.utils.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import unaldi.invoiceservice.utils.constant.ExceptionMessages;
import unaldi.invoiceservice.utils.exception.customExceptions.InvoiceNotFoundException;
import unaldi.invoiceservice.utils.exception.dto.ExceptionResponse;
import unaldi.invoiceservice.utils.result.DataResult;
import unaldi.invoiceservice.utils.result.ErrorDataResult;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleCreditCardNotFoundException(InvoiceNotFoundException exception, WebRequest request) {
        log.error("CreditCardNotFoundException occurred : " + exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.NOT_FOUND, request),
                        ExceptionMessages.INVOICE_NOT_FOUND)
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleAllException(Exception exception, WebRequest request) {
        log.error("Exception occurred : " + exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.BAD_REQUEST, request),
                        ExceptionMessages.BAD_REQUEST)
                );
    }

    private ExceptionResponse prepareExceptionResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
        NativeWebRequest nativeRequest = (NativeWebRequest) request;
        HttpServletRequest servletRequest = nativeRequest.getNativeRequest(HttpServletRequest.class);

        String httpMethod = servletRequest != null ? servletRequest.getMethod() : "Unknown";
        String requestPath = servletRequest != null ? servletRequest.getRequestURI() : "Unknown";

        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .httpStatusCode(httpStatus.value())
                .httpMethod(httpMethod)
                .errorType(exception.getClass().getSimpleName())
                .requestPath(requestPath)
                .build();
    }
}