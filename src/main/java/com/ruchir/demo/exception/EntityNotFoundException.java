package com.ruchir.demo.exception;

import com.ruchir.demo.enums.ExceptionCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private final ExceptionCode exceptionCode;

    public EntityNotFoundException(String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, ExceptionCode.R101.getDefaultMessage()));
        this.exceptionCode = ExceptionCode.R101;
    }

    public EntityNotFoundException(ExceptionCode exceptionCode, String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()));
        this.exceptionCode = exceptionCode;
    }

    public EntityNotFoundException(ExceptionCode exceptionCode, Throwable cause, String errorMessage) {
        super(StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()), cause);
        this.exceptionCode = exceptionCode;
    }
}
