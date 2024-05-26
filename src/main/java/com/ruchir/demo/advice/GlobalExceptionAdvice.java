package com.ruchir.demo.advice;

import com.ruchir.demo.dto.response.ErrorResponse;
import com.ruchir.demo.enums.ExceptionCode;
import com.ruchir.demo.exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String missingFields = ex.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField()+" :: "+fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        var response = new ErrorResponse();
        response.setExceptionId(status.toString());
        response.setExceptionCode(status.toString());
        response.setException(missingFields);
        response.setStatusCode(String.valueOf(status.value()));

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(DemoServiceException.class)
    public ResponseEntity<ErrorResponse> handleDemoServiceException(DemoServiceException exception) {
        return getErrorResponseResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception, exception.getExceptionCode());
    }
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception) {
        return getErrorResponseResponseEntity(HttpStatus.BAD_REQUEST, exception, exception.getExceptionCode());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception){
        return getErrorResponseResponseEntity(HttpStatus.NOT_FOUND, exception,ExceptionCode.R101);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        return getErrorResponseResponseEntity(HttpStatus.BAD_REQUEST, exception, ExceptionCode.ERR002);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception exception) {
        return getErrorResponseResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception, ExceptionCode.U100);
    }

    private void printExceptionTrace(Exception exception, String errorId) {
        log.error("Error occurred. Error code :: {}, Error message : {}", errorId, exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(HttpStatus errorCode, Exception exception, ExceptionCode exceptionCode) {
        var errorId = UUID.randomUUID().toString();
        printExceptionTrace(exception, errorId);
        var errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(String.valueOf(errorCode.value()));
        errorResponse.setException(exception.getMessage());
        errorResponse.setExceptionId(errorId);
        errorResponse.setExceptionCode(exceptionCode.getCode());
        return new ResponseEntity<>(errorResponse, errorCode);
    }
}
