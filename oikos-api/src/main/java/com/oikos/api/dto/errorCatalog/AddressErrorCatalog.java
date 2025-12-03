package com.oikos.api.dto.errorCatalog;

public class AddressErrorCatalog {
    private static final String ERROR_PREFIX = "ADDRESS-";

    public static final String POSTAL_CODE_SIZE_INVALID= ERROR_PREFIX + "001";
    public static final String POSTAL_CODE_REQUIRED = ERROR_PREFIX + "002";
    public static final String STREET_REQUIRED = ERROR_PREFIX + "003";
    public static final String STREET_NUMBER_REQUIRED = ERROR_PREFIX + "004";
    public static final String NEIGHBORHOOD_REQUIRED = ERROR_PREFIX + "005";
    public static final String CITY_REQUIRED = ERROR_PREFIX + "006";
}
