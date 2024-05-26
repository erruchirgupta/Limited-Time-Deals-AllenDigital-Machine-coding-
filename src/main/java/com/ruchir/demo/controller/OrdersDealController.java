package com.ruchir.demo.controller;

import com.ruchir.demo.dto.request.AddDealRequestDto;
import com.ruchir.demo.dto.request.PlaceOrderRequestDto;
import com.ruchir.demo.dto.response.WrappedResponse;
import com.ruchir.demo.services.executor.OrdersDealExecutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
@Tag(name = "Orders & Deal related API", description = "All operations related to Orders & Deal")
public class OrdersDealController {

    @Autowired
    private OrdersDealExecutorService executorService;


    @Operation(summary = "Place order API")
    @PostMapping(value = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrappedResponse<?>> placeOrder(@RequestBody PlaceOrderRequestDto requestDto) {
        return ResponseEntity.ok(executorService.placeOrder(requestDto));
    }

    @Operation(summary = "Add deal API (To be triggered by a seller, once stock(product) is added)")
    @PostMapping(value = "deal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrappedResponse<?>> addDeal(@RequestBody AddDealRequestDto requestDto) {
        return ResponseEntity.ok(executorService.addDeal(requestDto));
    }
}
