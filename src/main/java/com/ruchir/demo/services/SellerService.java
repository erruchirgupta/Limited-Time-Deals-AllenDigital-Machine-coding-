package com.ruchir.demo.services;

import com.ruchir.demo.constants.Constants;
import com.ruchir.demo.dto.request.OnboardSellerRequestDto;
import com.ruchir.demo.mapper.CustomMapper;
import com.ruchir.demo.repository.model.Seller;
import com.ruchir.demo.repository.service.SellerRepositoryService;
import com.ruchir.demo.utils.MDCUtils;
import com.ruchir.demo.utils.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerService {

    @Autowired
    private SellerRepositoryService sellerRepoService;

    @Autowired
    private CustomMapper mapper;

    @Transactional("demoPrimaryTransactionManager")
    public void onboardSeller(OnboardSellerRequestDto requestDto) {
        MDCUtils.put(Constants.MDC_TRIGGERED_BY, requestDto.getTriggeredBy());
        ValidationHelper.isTrue(sellerRepoService.findSellerByExistingData(requestDto).isEmpty(), "A seller already exists with either aadharNumber, panNumber, contact, email or gstin");
        sellerRepoService.save(mapper.toSeller(requestDto));
    }

    public Seller validateAndGetSellerById(Integer sellerId) {
        return sellerRepoService.validateAndGetSellerById(sellerId);
    }
}
