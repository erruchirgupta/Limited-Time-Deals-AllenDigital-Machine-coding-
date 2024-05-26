package com.ruchir.demo.repository.projection;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.ruchir.demo.repository.model.DealsConfig}
 */
public interface DealsConfigInfoProjection {
    Integer getId();

    LocalDateTime getDealStartTime();

    LocalDateTime getDealEndTime();

    String getDealBanner();

    Double getDiscount();

    Integer getSaleQuantity();

    Integer getStatus();
}