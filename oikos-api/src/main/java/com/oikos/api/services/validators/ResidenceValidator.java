package com.oikos.api.services.validators;

import com.oikos.api.dto.errorCatalog.ResidenceErrorCatalog;
import com.oikos.api.dto.residence.ResidenceCreateDTO;
import com.oikos.api.dto.user.UserResponseDTO;
import com.oikos.api.exceptions.OikosException;
import com.oikos.api.repository.ResidenceRepository;
import com.oikos.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ResidenceValidator {
    @Autowired
    UserService userService;
    @Autowired
    ResidenceRepository residenceRepository;

    public void validateOwnerIsRequester(ResidenceCreateDTO dto) {
        UserResponseDTO userDto = userService.findRequester();
        if(!userDto.getUuid().equals(dto.getOwnerId())){
            throw new RuntimeException(
                new OikosException(ResidenceErrorCatalog.CANNOT_CREATE_RESIDENCE_FOR_ANOTHER_USER)
            );
        }
    }

    public void validateResidenceNameIsUniqueForOwner(ResidenceCreateDTO dto) {
       if(residenceRepository.existsByOwnerIdAndName(dto.getOwnerId(),dto.getName())){
           throw new RuntimeException(
               new OikosException(ResidenceErrorCatalog.DUPLICATE_RESIDENCE_NAME_FOR_OWNER)
           );
       }
    }

    public void validateUsableAreaIsLessThanTotalArea(ResidenceCreateDTO dto) {
        if(dto.getUsableArea().compareTo(dto.getTotalArea()) > 0 ){
            throw new RuntimeException(
                new OikosException(ResidenceErrorCatalog.USABLE_AREA_IS_BIGGER_THAN_TOTAL_AREA)
            );
        }
    }

    public void validatePropertyTypeRules(ResidenceCreateDTO dto) {
        switch (dto.getPropertyType()){
            case APARTMENT:
                validateHaveUsableArea(dto);
                validateResidencialRules(dto);
                break;
            case HOUSE:
                validateHaveTotalArea(dto);
                break;
            case KITNET, STUDIO, DUPLEX:
                validateResidencialRules(dto);
                break;
        }
    }

    private void validateHaveTotalArea(ResidenceCreateDTO dto) {
        if(dto.getTotalArea().compareTo(BigDecimal.ZERO) <=0){
            throw new RuntimeException(
                new OikosException(ResidenceErrorCatalog.TOTAL_AREA_MUST_BE_POSITIVE_FOR_HOUSE)
            );
        }
    }

    private void validateHaveUsableArea(ResidenceCreateDTO dto) {
        if(dto.getUsableArea().compareTo(BigDecimal.ZERO) <=0){
            throw new RuntimeException(
                new OikosException(ResidenceErrorCatalog.USABLE_AREA_MUST_BE_POSITIVE_FOR_APARTMENT)
            );
        }
    }

    private void validateResidencialRules(ResidenceCreateDTO dto) {
        validateHaveBathroom(dto);
        validateHaveBedroom(dto);
    }

    private void validateHaveBedroom(ResidenceCreateDTO dto) {
        if(dto.getBedrooms()<=0){
            throw new RuntimeException(
                new OikosException(ResidenceErrorCatalog.BEDROOM_MUST_BE_POSITIVE_FOR_RESIDENTIAL)
            );
        }
    }

    private void validateHaveBathroom(ResidenceCreateDTO dto) {
        if(dto.getBathrooms()<=0){
            throw new RuntimeException(
                new OikosException(ResidenceErrorCatalog.BATHROOM_MUST_BE_POSITIVE_FOR_RESIDENTIAL)
            );
        }
    }
}
