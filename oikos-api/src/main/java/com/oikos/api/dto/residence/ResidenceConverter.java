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
                .ownerId(entity.getOwnerId())
                .name(entity.getName())
                .propertyType(entity.getPropertyType())
                .bedrooms(entity.getBedrooms())
                .bathrooms(entity.getBathrooms())
                .garageSpots(entity.getGarageSpots())
                .usableArea(entity.getUsableArea())
                .totalArea(entity.getTotalArea())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .address(addressConverter.toDto(entity.getAddress()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    public Residence toEntity(ResidenceCreateDTO dto){
        return Residence.builder()
                .ownerId(dto.getOwnerId())
                .name(dto.getName())
                .propertyType(dto.getPropertyType())
                .bedrooms(dto.getBedrooms())
                .bathrooms(dto.getBathrooms())
                .garageSpots(dto.getGarageSpots())
                .usableArea(dto.getUsableArea())
                .totalArea(dto.getTotalArea())
                .status(dto.getStatus())
                .description(dto.getDescription())
                .build();
    }
}
