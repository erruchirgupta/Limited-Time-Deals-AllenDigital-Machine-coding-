package com.ruchir.demo.controller;

import com.ruchir.demo.dto.request.OnboardCustomerRequestDto;
import com.ruchir.demo.dto.response.WrappedResponse;
import com.ruchir.demo.services.executor.CustomerExecutorService;
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
@RequestMapping("v1/customer")
@Tag(name = "Customer related API", description = "All operations related to customer")
public class CustomerController {

    @Autowired
    private CustomerExecutorService executorService;

    @Operation(summary = "OnBoard customer API")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrappedResponse<?>> onboardCustomer(@RequestBody OnboardCustomerRequestDto requestDto) {
        return ResponseEntity.ok(executorService.onboardCustomer(requestDto));
    }
}
