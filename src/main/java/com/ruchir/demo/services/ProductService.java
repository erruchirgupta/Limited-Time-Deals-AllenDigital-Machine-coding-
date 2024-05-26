package com.ruchir.demo.services;

import com.ruchir.demo.constants.Constants;
import com.ruchir.demo.dto.request.AddProductRequestDto;
import com.ruchir.demo.dto.request.InwardStockRequestDto;
import com.ruchir.demo.mapper.CustomMapper;
import com.ruchir.demo.repository.model.Products;
import com.ruchir.demo.repository.model.Seller;
import com.ruchir.demo.repository.service.ProductsRepositoryService;
import com.ruchir.demo.utils.MDCUtils;
import com.ruchir.demo.utils.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductsRepositoryService productsRepoService;

    @Autowired
    private CustomMapper mapper;

    public Products validateAndGetProductById(Integer id){
        return productsRepoService.validateAndGetProductById(id);
    }

    public Products save(Products product) {
        return productsRepoService.save(product);
    }

    public void addProduct(AddProductRequestDto requestDto, Seller seller) {
        MDCUtils.put(Constants.MDC_TRIGGERED_BY, requestDto.getTriggeredBy());
        ValidationHelper.isTrue(productsRepoService.findProductsByName(requestDto.getName()).isEmpty(), String.format("A duplicate product already by name: %s", requestDto.getName()));
        save(mapper.toProduct(requestDto, seller));
    }

    public void inwardStock(InwardStockRequestDto requestDto) {
        MDCUtils.put(Constants.MDC_TRIGGERED_BY, requestDto.getTriggeredBy());
        Products products = productsRepoService.validateAndGetProductById(requestDto.getProductId());
        products.setTotalStock(products.getTotalStock() + requestDto.getStockQuantity());
        products.setRemainingStock(products.getRemainingStock() + requestDto.getStockQuantity());
        save(products);
    }
}
