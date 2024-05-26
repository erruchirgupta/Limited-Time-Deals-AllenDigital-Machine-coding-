package com.ruchir.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    @Builder.Default
    private String statusCode = "200";

    @Builder.Default
    private String statusMessage = "success";


    public void setStatusCode(String statusCode) {
        Optional.ofNullable(statusCode).ifPresent(sc -> this.statusCode = sc);
    }

    public void setStatusMessage(String statusMessage) {
        Optional.ofNullable(statusMessage).ifPresent(sm -> this.statusMessage = sm);
    }

}
