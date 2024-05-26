package com.ruchir.demo.repository.service;

import com.ruchir.demo.repository.model.OrderItems;
import com.ruchir.demo.repository.repo.OrderItemsRepository;
import com.ruchir.demo.repository.repo.readonly.OrderItemsReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class OrderItemsRepositoryService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private OrderItemsReadRepository orderItemsReadRepository;

    public void save(List<OrderItems> list) {
        orderItemsRepository.saveAll(list);
    }
}
