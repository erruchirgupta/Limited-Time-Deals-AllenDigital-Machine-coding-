package com.ruchir.demo.mapper;

import com.ruchir.demo.dto.request.*;
import com.ruchir.demo.repository.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    @Mapping(target = "amountToBeCollected", source = "requestDto.amountCollected")
    @Mapping(target = "storeId", constant = "1")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "id", ignore = true)
    Orders toOrders(PlaceOrderRequestDto requestDto, Customer customer, LocalDateTime orderProcessingTime);

    @Mapping(target = "pricePerUnit", expression = "java(product.getMaxSellingPrice() * (dealsConfig.getDiscount()/100.0))")
    @Mapping(target = "id", ignore = true)
    OrderItems toOrderItems(DealsConfig dealsConfig, Products product, Orders orders, OrderItemsRequest orderItemsRequest);

    @Mapping(target = "status", constant = "true")
    @Mapping(target = "id", ignore = true)
    Seller toSeller(OnboardSellerRequestDto requestDto);

    @Mapping(target = "product", source = "product")
    @Mapping(target = "saleQuantityRemaining", source = "requestDto.totalSaleQuantity")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "id", ignore = true)
    DealsConfig toDealsConfig(AddDealRequestDto requestDto, Products product);

    @Mapping(target = "name", source = "requestDto.name")
    @Mapping(target = "remainingStock", source = "requestDto.totalStock")
    @Mapping(target = "seller", source = "seller")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    Products toProduct(AddProductRequestDto requestDto, Seller seller);

    @Mapping(target = "id", ignore = true)
    Customer toCustomer(OnboardCustomerRequestDto requestDto);
}
