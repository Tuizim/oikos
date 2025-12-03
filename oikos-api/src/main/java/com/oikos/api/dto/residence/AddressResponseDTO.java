package com.oikos.api.dto.residence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
