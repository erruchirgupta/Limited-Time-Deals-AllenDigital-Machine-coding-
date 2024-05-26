package com.ruchir.demo.services;

import com.ruchir.demo.constants.Constants;
import com.ruchir.demo.dto.request.OnboardCustomerRequestDto;
import com.ruchir.demo.mapper.CustomMapper;
import com.ruchir.demo.repository.service.CustomerRepositoryService;
import com.ruchir.demo.utils.MDCUtils;
import com.ruchir.demo.utils.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepositoryService customerRepoService;

    @Autowired
    private CustomMapper mapper;

    @Transactional("demoPrimaryTransactionManager")
    public void onboardCustomer(OnboardCustomerRequestDto requestDto) {
        MDCUtils.put(Constants.MDC_TRIGGERED_BY, requestDto.getTriggeredBy());
        ValidationHelper.isTrue(customerRepoService.findCustomerByContact(requestDto.getContact()).isEmpty(), String.format("A duplicate Customer already by contact: %s", requestDto.getContact()));
        customerRepoService.save(mapper.toCustomer(requestDto));
    }
}
