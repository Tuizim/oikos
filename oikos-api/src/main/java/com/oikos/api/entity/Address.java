package com.oikos.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="postal_code",nullable = false,length = 8)
    private String postal_code;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false, length = 20)
    private String number;

    @Column(name = "complement", nullable = false)
    private String complement;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Builder.Default
    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", insertable = false,
            columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
