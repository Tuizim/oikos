package com.oikos.api.entity;

import com.oikos.api.enums.ImageType;
import com.oikos.api.enums.PropertyType;
import com.oikos.api.enums.ResidenceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "residences")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "owner_id")
    private UUID owner_id;

    @Column(nullable = false,name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type", nullable = false)
    private PropertyType propertyType;

    @Column(nullable = false, name = "bedrooms")
    private Integer bedrooms;

    @Column(nullable = false, name = "bathrooms")
    private Integer bathrooms;

    @Column(nullable = false, name = "garage_spots")
    private Integer garage_spots;

    @Column(name = "usable_area", precision = 10, scale = 2)
    private BigDecimal usableArea;

    @Column(name = "total_area", precision = 10, scale = 2)
    private BigDecimal total_area;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ResidenceStatus status;

    @Column(nullable = false, name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Builder.Default
    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", insertable = false,
            columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
