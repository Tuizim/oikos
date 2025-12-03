package com.oikos.api.dto.residence;

import com.oikos.api.enums.ImageType;
import com.oikos.api.enums.PropertyType;
import com.oikos.api.enums.ResidenceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidenceCreateDTO {

        @NotNull
        private UUID ownerId;

        @NotBlank
        private String name;

        @NotNull
        private PropertyType propertyType;

        @NotNull
        private Integer bedrooms;

        @NotNull
        private Integer bathrooms;

        @NotNull
        private Integer garageSpots;

        private BigDecimal usableArea;

        private BigDecimal totalArea;

        @NotNull
        private ResidenceStatus status;

        @NotBlank
        private String description;

        @NotNull
        private AddressCreateDTO address;
}
