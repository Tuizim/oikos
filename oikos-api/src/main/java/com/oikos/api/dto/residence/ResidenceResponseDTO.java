package com.oikos.api.dto.residence;

import com.oikos.api.enums.ImageType;
import com.oikos.api.enums.PropertyType;
import com.oikos.api.enums.ResidenceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidenceResponseDTO {

    private Long id;

    private UUID ownerId;

    private String name;

    private PropertyType propertyType;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer garageSpots;

    private BigDecimal usableArea;

    private BigDecimal totalArea;

    private ResidenceStatus status;

    private String description;

    private AddressResponseDTO address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
