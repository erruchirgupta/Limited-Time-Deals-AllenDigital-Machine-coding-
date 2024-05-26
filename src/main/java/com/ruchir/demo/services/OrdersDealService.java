package com.ruchir.demo.services;

import com.ruchir.demo.constants.Constants;
import com.ruchir.demo.dto.request.AddDealRequestDto;
import com.ruchir.demo.dto.request.PlaceOrderRequestDto;
import com.ruchir.demo.mapper.CustomMapper;
import com.ruchir.demo.repository.model.*;
import com.ruchir.demo.repository.service.*;
import com.ruchir.demo.utils.MDCUtils;
import com.ruchir.demo.utils.ValidationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class OrdersDealService {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemsRepositoryService orderItemsRepositoryService;

    @Autowired
    private OrdersRepositoryService ordersRepositoryService;

    @Autowired
    private CustomerRepositoryService customerRepositoryService;

    @Autowired
    private DealsConfigRepositoryService dealsConfigRepositoryService;

    @Autowired
    private CustomMapper mapper;

    @Transactional("demoPrimaryTransactionManager")
    public void addDeal(AddDealRequestDto requestDto) {
        MDCUtils.put(Constants.MDC_TRIGGERED_BY, requestDto.getTriggeredBy());
        Seller seller = sellerService.validateAndGetSellerById(requestDto.getSellerId());
        Products product = productService.validateAndGetProductById(requestDto.getProductId());
        ValidationHelper.isTrue(Objects.equals(seller.getId(), product.getSeller().getId()), "Product must belong to the seller, to add a deal!");
        dealsConfigRepositoryService.save(mapper.toDealsConfig(requestDto, product));
    }

    @Transactional("demoPrimaryTransactionManager")
    public void placeOrder(PlaceOrderRequestDto requestDto) {
        MDCUtils.put(Constants.MDC_TRIGGERED_BY, requestDto.getTriggeredBy());
        Customer customer = customerRepositoryService.validateAndGetCustomerById(requestDto.getCustomerId());
        Map<Integer, DealsConfig> productDealsConfigMap = new HashMap<>();
        requestDto.getPlacedItems()
                .forEach(
                        orderItemsRequest -> {
                            Products product = productService.validateAndGetProductById(orderItemsRequest.getProductId());
                            DealsConfig dealsConfig = dealsConfigRepositoryService.findByAvailableDealByProduct(product);
                            ValidationHelper.isTrue(orderItemsRequest.getQuantity() <= dealsConfig.getMaxQuantityPerOrder(), "Max quantity limit per order breached!");
                            ValidationHelper.isTrue(product.getRemainingStock() > 0, "Inventory out of stock!");
                            productDealsConfigMap.put(product.getId(), dealsConfig);
                        }
                );

        Orders orders = ordersRepositoryService.save(mapper.toOrders(requestDto, customer, LocalDateTime.now()));
        List<OrderItems> list = requestDto.getPlacedItems()
                .stream()
                .map(
                        orderItemsRequest -> {
                            DealsConfig dealsConfig = productDealsConfigMap.get(orderItemsRequest.getProductId());
                            dealsConfig.setSaleQuantityRemaining(dealsConfig.getSaleQuantityRemaining() - orderItemsRequest.getQuantity());
                            dealsConfig = dealsConfigRepositoryService.save(dealsConfig);
                            dealsConfig.getProduct().setRemainingStock(dealsConfig.getProduct().getRemainingStock() - orderItemsRequest.getQuantity());
                            productService.save(dealsConfig.getProduct());
                            return mapper.toOrderItems(dealsConfig, dealsConfig.getProduct(), orders, orderItemsRequest);
                        }
                ).toList();
        orderItemsRepositoryService.save(list);
    }
}
