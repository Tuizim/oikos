package com.oikos.api.dto.residence;
import lombok.Data;

@Data
public class CityResponseDTO {
    private Long id;
    private String name;
    private String stateCode; // UF
}