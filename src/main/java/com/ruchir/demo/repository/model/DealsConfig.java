package com.ruchir.demo.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "deals_config")
@Entity
public class DealsConfig extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @ToString.Exclude
    private Seller seller;

    @Column(name = "deal_start_time", nullable = false)
    private LocalDateTime dealStartTime;

    @Column(name = "deal_end_time", nullable = false)
    private LocalDateTime dealEndTime;

    @Column(name = "deal_banner")
    private String dealBanner;

    @ManyToOne
    @JoinColumn(name = "product_id_on_sale", nullable = false)
    @ToString.Exclude
    private Products product;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "max_quantity_per_order", nullable = false)
    private Integer maxQuantityPerOrder;

    @Column(name = "total_sale_quantity", nullable = false)
    private Integer totalSaleQuantity;

    @Column(name = "sale_quantity_remaining", nullable = false)
    private Integer saleQuantityRemaining;

    @Builder.Default
    @Column(name = "status", nullable = false)
    private Boolean status = Boolean.TRUE;
}
