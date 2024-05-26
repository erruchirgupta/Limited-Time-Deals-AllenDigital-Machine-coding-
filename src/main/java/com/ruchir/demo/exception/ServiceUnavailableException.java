package com.ruchir.demo.exception;

import com.ruchir.demo.enums.ExceptionCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class ServiceUnavailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private final ExceptionCode exceptionCode;

    public ServiceUnavailableException(String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, ExceptionCode.S102.getDefaultMessage()));
        this.exceptionCode = ExceptionCode.S102;
    }

    public ServiceUnavailableException(ExceptionCode exceptionCode, String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()));
        this.exceptionCode = exceptionCode;
    }

    public ServiceUnavailableException(ExceptionCode exceptionCode, Throwable cause, String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()), cause);
        this.exceptionCode = exceptionCode;
    }
}
