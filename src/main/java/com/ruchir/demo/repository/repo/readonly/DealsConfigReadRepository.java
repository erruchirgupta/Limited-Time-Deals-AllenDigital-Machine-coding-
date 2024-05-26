package com.ruchir.demo.repository.repo.readonly;

import com.ruchir.demo.repository.model.DealsConfig;
import com.ruchir.demo.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

@ReadOnlyRepository
public interface DealsConfigReadRepository extends JpaRepository<DealsConfig, Integer> {

    @Query("select d from DealsConfig d where d.product.id = ?1 and d.dealStartTime <= ?2 and d.dealEndTime >= ?2 and d.saleQuantityRemaining > ?3")
    Optional<DealsConfig> findByProduct_idAndDealStartTimeLessThanEqualAndDealEndTimeGreaterThanEqualAndSaleQuantityRemainingGreaterThan(Integer productId, LocalDateTime currentTime, Integer quantity);
}
