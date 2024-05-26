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
@Table(name = "products")
@Entity
public class Products extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Version
    private Long version;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uom", nullable = false)
    private String uom;

    @Column(name = "unit_value", nullable = false)
    private String unitValue;

    @Column(name = "max_selling_price", nullable = false)
    private Double maxSellingPrice;

    @Column(name = "remaining_stock", nullable = false)
    private Integer remainingStock;

    @Column(name = "total_stock", nullable = false)
    private Integer totalStock;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @ToString.Exclude
    private Seller seller;

    @Builder.Default
    @Column(name = "status", nullable = false)
    private Boolean status = Boolean.TRUE;
}