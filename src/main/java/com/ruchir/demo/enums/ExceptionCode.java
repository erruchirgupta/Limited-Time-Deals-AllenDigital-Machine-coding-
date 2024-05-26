package com.ruchir.demo.enums;

import lombok.Getter;

public enum ExceptionCode {

    U100("U100", "Unknown exception occurred."),
    R101("R101", "Resource not found."),
    S102("S102", "Service temporarily unavailable."),
    ERR001("ERR001", "Call to external service failed!"),
    ERR002("ERR002", "Validation Error Occurred!"),
    ERR003("ERR003", "Deal expired or product out of stock!");


    @Getter
    private final String code;

    @Getter
    private final String defaultMessage;

    ExceptionCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
