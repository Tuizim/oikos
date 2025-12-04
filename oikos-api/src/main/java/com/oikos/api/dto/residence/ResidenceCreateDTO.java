package com.oikos.api.dto.residence;

import com.oikos.api.dto.errorCatalog.ResidenceErrorCatalog;
import com.oikos.api.enums.PropertyType;
import com.oikos.api.enums.ResidenceStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

        @NotNull(message = ResidenceErrorCatalog.OWNER_REQUIRED)
        private UUID ownerId;

        @NotBlank(message = ResidenceErrorCatalog.NAME_REQUIRED)
        private String name;

        @NotNull(message = ResidenceErrorCatalog.PROPERTY_TYPE_REQUIRED)
        private PropertyType propertyType;

        @NotNull
        @Min(value = 0, message = ResidenceErrorCatalog.BEDROOMS_NEGATIVE)
        private Integer bedrooms;

        @NotNull
        @Min(value = 0, message = ResidenceErrorCatalog.BATHROOMS_NEGATIVE)
        private Integer bathrooms;

        @NotNull
        @Min(value = 0, message = ResidenceErrorCatalog.GARAGE_SPOT_NEGATIVE)
        private Integer garageSpots;

        @Min(value = 0, message = ResidenceErrorCatalog.USABLE_AREA_NEGATIVE)
        private BigDecimal usableArea;

        @Min(value = 0, message = ResidenceErrorCatalog.TOTAL_AREA_NEGATIVE)
        private BigDecimal totalArea;

        @NotNull(message = ResidenceErrorCatalog.STATUS_REQUIRED)
        private ResidenceStatus status;

        @NotBlank(message = ResidenceErrorCatalog.DESCRIPTION_REQUIRED)
        private String description;

        @NotNull(message = ResidenceErrorCatalog.ADDRESS_REQUIRED)
        @Valid
        private AddressCreateDTO address;
}
