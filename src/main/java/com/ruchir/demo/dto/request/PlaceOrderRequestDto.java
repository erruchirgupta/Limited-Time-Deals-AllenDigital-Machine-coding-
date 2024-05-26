package com.ruchir.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceOrderRequestDto implements Serializable {

    private Integer customerId;
    private List<OrderItemsRequest> placedItems;
    private String paymentType;
    private String paymentStatus;
    private String txnId;
    private Double amountCollected;
    private String triggeredBy;

}
