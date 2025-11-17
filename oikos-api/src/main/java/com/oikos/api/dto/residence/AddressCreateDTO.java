package com.oikos.api.dto.residence;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddressCreateDTO {

    @NotBlank
    private String postalCode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    private String reference;

    @NotBlank
    private String neighborhood;

    @NotNull
    private Long cityId;
}
