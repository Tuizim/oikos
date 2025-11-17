package com.oikos.api.dto.residence;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressResponseDTO {

    private Long id;

    private String postalCode;

    private String street;

    private String number;

    private String complement;

    private String reference;

    private String neighborhood;

    private CityResponseDTO city;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
