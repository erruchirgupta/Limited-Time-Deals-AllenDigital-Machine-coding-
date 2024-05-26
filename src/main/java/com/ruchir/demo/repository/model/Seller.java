package com.ruchir.demo.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "seller")
@Entity
public class Seller extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "aadhar_number", nullable = false)
    private String aadharNumber;

    @Column(name = "pan_number", nullable = false)
    private String panNumber;

    @Column(name = "contact", nullable = false)
    private Long contact;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "gstin", nullable = false)
    private String gstin;

    @Builder.Default
    @Column(name = "status", nullable = false)
    private Boolean status = Boolean.TRUE;
}
