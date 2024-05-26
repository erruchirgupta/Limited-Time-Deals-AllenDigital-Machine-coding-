package com.ruchir.demo.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "orders")
@Entity
public class Orders extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private Customer customer;

    @Column(name = "store_id")
    private String storeId;

    @Builder.Default
    @Column(name = "status", nullable = false)
    private Boolean status = Boolean.TRUE;

    @Column(name = "order_processing_time", nullable = false)
    private LocalDateTime orderProcessingTime;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "txn_id")
    private String txnId;

    @Builder.Default
    @Column(name = "amt_to_be_collected", nullable = false)
    private Double amountToBeCollected = 0D;

    @Builder.Default
    @Column(name = "amt_collected", nullable = false)
    private Double amountCollected = 0D;
}
