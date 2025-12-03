package com.oikos.api.dto.residence;

import com.oikos.api.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {
    public CityResponseDTO toDto(City entidade){
        return CityResponseDTO.builder()
                .id(entidade.getId())
                .name(entidade.getName())
                .stateCode(entidade.getState().getCode())
                .build();
    }
}
