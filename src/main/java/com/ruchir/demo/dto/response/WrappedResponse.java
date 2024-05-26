package com.ruchir.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruchir.demo.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WrappedResponse<T> implements Serializable {
    T data;
    @Builder.Default
    private String statusCode = "200";
    @Builder.Default
    private String statusMessage = "Success";

    public WrappedResponse(T payload){
        this(payload, ResponseCode.SUCCESS);
    }

    public WrappedResponse(T payload, ResponseCode statusCode) {
        this.data = payload;
        this.statusCode = statusCode.getCode();
        this.statusMessage = statusCode.getDefaultMessage();
    }
}
