package com.oikos.api.dto.residence;

import com.oikos.api.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    @Autowired
    CityConverter cityConverter;
    public AddressResponseDTO toDto(Address entity){
        return AddressResponseDTO.builder()
                .id(entity.getId())
                .postalCode(entity.getPostal_code())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .complement(entity.getComplement())
                .reference(entity.getReference())
                .neighborhood(entity.getNeighborhood())
                .city(cityConverter.toDto(entity.getCity()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
