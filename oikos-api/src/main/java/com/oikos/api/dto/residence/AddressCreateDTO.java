package com.oikos.api.dto.residence;

import com.oikos.api.dto.errorCatalog.AddressErrorCatalog;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddressCreateDTO {

    @NotBlank(message = AddressErrorCatalog.POSTAL_CODE_REQUIRED)
    @Size(min = 8, max = 8,message = AddressErrorCatalog.POSTAL_CODE_SIZE_INVALID)
    private String postalCode;

    @NotBlank(message = AddressErrorCatalog.STREET_REQUIRED)
    private String street;

    @NotBlank(message = AddressErrorCatalog.STREET_NUMBER_REQUIRED)
    private String number;

    private String complement;

    private String reference;

    @NotBlank(message = AddressErrorCatalog.NEIGHBORHOOD_REQUIRED)
    private String neighborhood;

    @NotNull(message = AddressErrorCatalog.CITY_REQUIRED)
    private Long cityId;
}
