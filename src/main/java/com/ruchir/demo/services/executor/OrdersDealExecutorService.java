package com.ruchir.demo.services.executor;

import com.ruchir.demo.dto.request.AddDealRequestDto;
import com.ruchir.demo.dto.request.PlaceOrderRequestDto;
import com.ruchir.demo.dto.response.WrappedResponse;
import com.ruchir.demo.services.OrdersDealService;
import com.ruchir.demo.utils.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Service
public class OrdersDealExecutorService {

    @Autowired
    private OrdersDealService ordersDealService;

    public WrappedResponse<?> placeOrder(PlaceOrderRequestDto requestDto) {
        ValidationHelper.isTrue(!CollectionUtils.isEmpty(requestDto.getPlacedItems()), "Order must contains items!");
        requestDto.getPlacedItems()
                        .forEach(orderItemsRequest -> {
                            ValidationHelper.isNotZero(orderItemsRequest.getQuantity(), "Product quantity must be more than zero!");
                        });
        ordersDealService.placeOrder(requestDto);
        return new WrappedResponse<>();
    }

    public WrappedResponse<?> addDeal(AddDealRequestDto requestDto) {
        ordersDealService.addDeal(requestDto);
        return new WrappedResponse<>();
    }
}
