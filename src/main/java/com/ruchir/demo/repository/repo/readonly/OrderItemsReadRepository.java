package com.ruchir.demo.repository.repo.readonly;

import com.ruchir.demo.repository.model.OrderItems;
import com.ruchir.demo.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@ReadOnlyRepository
public interface OrderItemsReadRepository extends JpaRepository<OrderItems, Integer> {
}
