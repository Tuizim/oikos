package com.oikos.api.services;

import com.oikos.api.dto.residence.*;
import com.oikos.api.entity.Address;
import com.oikos.api.entity.City;
import com.oikos.api.entity.Residence;
import com.oikos.api.repository.ResidenceRepository;
import com.oikos.api.services.validators.ResidenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceService {
    @Autowired
    ResidenceRepository residenceRepository;
    @Autowired
    ResidenceConverter residenceConverter;
    @Autowired
    AddressConverter addressConverter;
    @Autowired
    AddressService addressService;
    @Autowired
    ResidenceValidator residenceValidator;

    public ResidenceResponseDTO create(ResidenceCreateDTO dto){
        validateBusinessRulesToCreate(dto);
        Residence entity = residenceConverter.toEntity(dto);
        addAddressToEntity(entity,dto.getAddress());
        residenceRepository.save(entity);
        return residenceConverter.toDto(entity);
    }

    private void addAddressToEntity(Residence entity, AddressCreateDTO dto) {
       Address address = addressConverter.toEntity(dto);
       City city = addressService.findCityById(dto.getCityId());
       address.setCity(city);
       entity.setAddress(address);
    }

    private void validateBusinessRulesToCreate(ResidenceCreateDTO dto) {
        residenceValidator.validateOwnerIsRequester(dto);
        residenceValidator.validateResidenceNameIsUniqueForOwner(dto);
        residenceValidator.validateUsableAreaIsLessThanTotalArea(dto);
        residenceValidator.validatePropertyTypeRules(dto);
    }
}
