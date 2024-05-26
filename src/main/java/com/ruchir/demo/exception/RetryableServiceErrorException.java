package com.ruchir.demo.exception;

import com.ruchir.demo.enums.ExceptionCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class RetryableServiceErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private final ExceptionCode exceptionCode;

    public RetryableServiceErrorException(String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, ExceptionCode.U100.getDefaultMessage()));
        this.exceptionCode = ExceptionCode.U100;
    }

    public RetryableServiceErrorException(ExceptionCode exceptionCode, String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()));
        this.exceptionCode = exceptionCode;
    }

    public RetryableServiceErrorException(ExceptionCode exceptionCode, Throwable cause, String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()), cause);
        this.exceptionCode = exceptionCode;
    }
}
