package com.ruchir.demo.controller;

import com.ruchir.demo.dto.request.AddProductRequestDto;
import com.ruchir.demo.dto.request.InwardStockRequestDto;
import com.ruchir.demo.dto.request.OnboardSellerRequestDto;
import com.ruchir.demo.dto.response.WrappedResponse;
import com.ruchir.demo.services.executor.SellerProductExecutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
@Tag(name = "Seller & Product related API", description = "All operations related to Seller & Product")
public class SellerProductController {

    @Autowired
    private SellerProductExecutorService executorService;

    @Operation(summary = "OnBoard seller API")
    @PostMapping(value = "seller", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrappedResponse<?>> onboardSeller(@RequestBody OnboardSellerRequestDto requestDto) {
        return ResponseEntity.ok(executorService.onboardSeller(requestDto));
    }

    @Operation(summary = "Upload stock/product API")
    @PostMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrappedResponse<?>> addProduct(@RequestBody AddProductRequestDto requestDto) {
        return ResponseEntity.ok(executorService.addProduct(requestDto));
    }

    @Operation(summary = "inward stock API (Adding stock for an existing product)")
    @PatchMapping(value = "product/inward", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrappedResponse<?>> inwardStock(@RequestBody InwardStockRequestDto requestDto) {
        return ResponseEntity.ok(executorService.inwardStock(requestDto));
    }
}
