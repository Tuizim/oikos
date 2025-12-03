package com.oikos.api.dto.residence;

import com.oikos.api.entity.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResidenceConverter {
    @Autowired
    AddressConverter addressConverter;
    public ResidenceResponseDTO toDto(Residence entity){
        return ResidenceResponseDTO.builder()
                .id(entity.getId())
                .ownerId(entity.getOwner_id())
                .name(entity.getName())
                .propertyType(entity.getPropertyType())
                .bedrooms(entity.getBedrooms())
                .bathrooms(entity.getBathrooms())
                .garageSpots(entity.getGarage_spots())
                .usableArea(entity.getUsableArea())
                .totalArea(entity.getTotal_area())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .address(addressConverter.toDto(entity.getAddress()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
