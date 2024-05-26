package com.ruchir.demo.services.executor;

import com.ruchir.demo.dto.request.AddProductRequestDto;
import com.ruchir.demo.dto.request.InwardStockRequestDto;
import com.ruchir.demo.dto.request.OnboardSellerRequestDto;
import com.ruchir.demo.dto.response.WrappedResponse;
import com.ruchir.demo.services.ProductService;
import com.ruchir.demo.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerProductExecutorService {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    public WrappedResponse<?> onboardSeller(OnboardSellerRequestDto requestDto) {
        sellerService.onboardSeller(requestDto);
        return new WrappedResponse<>();
    }

    public WrappedResponse<?> addProduct(AddProductRequestDto requestDto) {
        productService.addProduct(requestDto, sellerService.validateAndGetSellerById(requestDto.getSellerId()));
        return new WrappedResponse<>();
    }

    public WrappedResponse<?> inwardStock(InwardStockRequestDto requestDto) {
        productService.inwardStock(requestDto);
        return new WrappedResponse<>();
    }
}
