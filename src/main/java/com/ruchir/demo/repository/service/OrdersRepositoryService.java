package com.ruchir.demo.repository.service;

import com.ruchir.demo.repository.model.Orders;
import com.ruchir.demo.repository.repo.OrdersRepository;
import com.ruchir.demo.repository.repo.readonly.OrdersReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class OrdersRepositoryService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrdersReadRepository ordersReadRepository;

    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }
}
