package com.ruchir.demo.services.executor;

import com.ruchir.demo.dto.request.OnboardCustomerRequestDto;
import com.ruchir.demo.dto.response.WrappedResponse;
import com.ruchir.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerExecutorService {

    @Autowired
    private CustomerService service;

    public WrappedResponse<?> onboardCustomer(OnboardCustomerRequestDto requestDto) {
        service.onboardCustomer(requestDto);
        return new WrappedResponse<>();
    }
}
