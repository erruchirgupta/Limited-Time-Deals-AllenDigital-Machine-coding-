package com.ruchir.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruchir.demo.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddDealRequestDto implements Serializable {

    private Integer sellerId;
    private Integer productId;
    private String dealBanner;
    private Double discount;
    @JsonFormat(pattern = DateUtil.DateFormat.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime dealStartTime;
    @JsonFormat(pattern = DateUtil.DateFormat.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime dealEndTime;
    private Integer maxQuantityPerOrder;
    private Integer totalSaleQuantity;
    private String triggeredBy;
}
