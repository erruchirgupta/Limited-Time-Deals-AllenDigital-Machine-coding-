package com.ruchir.demo.repository.service;

import com.ruchir.demo.constants.Messages;
import com.ruchir.demo.enums.ExceptionCode;
import com.ruchir.demo.exception.EntityNotFoundException;
import com.ruchir.demo.repository.model.Products;
import com.ruchir.demo.repository.repo.ProductsRepository;
import com.ruchir.demo.repository.repo.readonly.ProductsReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public class ProductsRepositoryService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsReadRepository productsReadRepository;

    public Optional<Products> findProductsById(Integer id){ return  productsReadRepository.findById(id); }

    public Optional<Products> findProductsByName(String name){ return  productsReadRepository.findByName(name); }

    public Products validateAndGetProductById(Integer id){
        return findProductsById(id)
                .orElseThrow(()->
                        new EntityNotFoundException(ExceptionCode.R101,
                                String.format(Messages.ENTITY_NOT_FOUND, "Products", "id", id)));
    }

    public Products validateAndGetProductByName(String name){
        return findProductsByName(name)
                .orElseThrow(()->
                        new EntityNotFoundException(ExceptionCode.R101,
                                String.format(Messages.ENTITY_NOT_FOUND, "Products", "name", name)));
    }

    @Retryable(retryFor = {OptimisticLockingFailureException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Products save(Products product) {
        return productsRepository.save(product);
    }
}
