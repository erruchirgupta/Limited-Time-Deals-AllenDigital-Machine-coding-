package com.ruchir.demo.enums;

import lombok.Getter;

public enum ResponseCode {

    SUCCESS("200", "Success");


    @Getter
    private final String code;

    @Getter
    private final String defaultMessage;

    ResponseCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
