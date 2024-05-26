package com.ruchir.demo.exception;

import com.ruchir.demo.enums.ExceptionCode;
import org.apache.commons.lang3.StringUtils;

public class ValidationException extends DemoServiceException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String errorMessage) {
        super(ExceptionCode.ERR002, StringUtils.defaultIfBlank(errorMessage, ExceptionCode.ERR002.getDefaultMessage()));
    }

    public ValidationException(ExceptionCode exceptionCode) {
        super(exceptionCode, exceptionCode.getDefaultMessage());
    }

    public ValidationException(ExceptionCode exceptionCode, String errorMessage) {
        super(exceptionCode, StringUtils.defaultIfBlank(errorMessage, exceptionCode.getDefaultMessage()));
    }
}
