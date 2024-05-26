package com.ruchir.demo.repository.repo.readonly;

import com.ruchir.demo.repository.model.Orders;
import com.ruchir.demo.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@ReadOnlyRepository
public interface OrdersReadRepository extends JpaRepository<Orders, Integer> {
}
