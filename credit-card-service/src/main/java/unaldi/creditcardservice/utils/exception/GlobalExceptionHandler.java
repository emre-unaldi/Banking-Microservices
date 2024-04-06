package unaldi.creditcardservice.utils.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import unaldi.creditcardservice.utils.constant.ExceptionMessages;
import unaldi.creditcardservice.utils.exception.customExceptions.BankNotFoundException;
import unaldi.creditcardservice.utils.exception.customExceptions.CreditCardNotFoundException;
import unaldi.creditcardservice.utils.exception.customExceptions.UserNotFoundException;
import unaldi.creditcardservice.utils.exception.dto.ExceptionResponse;
import unaldi.creditcardservice.utils.result.DataResult;
import unaldi.creditcardservice.utils.result.ErrorDataResult;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CreditCardNotFoundException.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleCreditCardNotFoundException(Exception exception, WebRequest request) {
        log.error("CreditCardNotFoundException occurred : " + exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.NOT_FOUND, request),
                        ExceptionMessages.CREDIT_CARD_NOT_FOUND)
                );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleUserNotFoundException(Exception exception, WebRequest request) {
        log.error("UserNotFoundException occurred : " + exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.NOT_FOUND, request),
                        ExceptionMessages.USER_NOT_FOUND)
                );
    }

    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<DataResult<ExceptionResponse>> handleBankNotFoundException(Exception exception, WebRequest request) {
        log.error("BankNotFoundException occurred : " + exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDataResult<>(
                        prepareExceptionResponse(exception, HttpStatus.NOT_FOUND, request),
                        ExceptionMessages.BANK_NOT_FOUND)
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
