package com.ruchir.demo.repository.service;

import com.ruchir.demo.enums.ExceptionCode;
import com.ruchir.demo.exception.ValidationException;
import com.ruchir.demo.repository.model.DealsConfig;
import com.ruchir.demo.repository.model.Products;
import com.ruchir.demo.repository.repo.DealsConfigRepository;
import com.ruchir.demo.repository.repo.readonly.DealsConfigReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DealsConfigRepositoryService {

    @Autowired
    private DealsConfigRepository dealsConfigRepository;
    @Autowired
    private DealsConfigReadRepository dealsConfigReadRepository;

    public Optional<DealsConfig> findByAvailableDealByProduct(Integer productId) {
        return dealsConfigReadRepository.findByProduct_idAndDealStartTimeLessThanEqualAndDealEndTimeGreaterThanEqualAndSaleQuantityRemainingGreaterThan(productId, LocalDateTime.now(), 0);
    }

    @Retryable(retryFor = {OptimisticLockingFailureException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public DealsConfig save(DealsConfig dealsConfig) {
        return dealsConfigRepository.save(dealsConfig);
    }

    public DealsConfig findByAvailableDealByProduct(Products product) {
        return findByAvailableDealByProduct(product.getId()).orElseThrow(()-> new ValidationException(ExceptionCode.ERR003));
    }
}
