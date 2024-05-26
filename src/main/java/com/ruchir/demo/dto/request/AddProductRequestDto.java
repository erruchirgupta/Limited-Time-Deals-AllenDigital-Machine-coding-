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
public class AddProductRequestDto implements Serializable {

    private Integer sellerId;
    private String name;
    private String uom;
    private String unitValue;
    private Double maxSellingPrice;
    private Integer totalStock;
    private String triggeredBy;
}
