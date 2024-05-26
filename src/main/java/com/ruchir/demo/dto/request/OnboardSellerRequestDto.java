package com.ruchir.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnboardSellerRequestDto implements Serializable {

    private String name;
    private String aadharNumber; //for unique seller onboarding
    private String panNumber; //for unique seller onboarding
    private Long contact;
    private String email;
    private String gstin; //for unique seller onboarding
    private String triggeredBy;
}
