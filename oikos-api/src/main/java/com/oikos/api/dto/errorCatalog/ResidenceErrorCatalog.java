package com.oikos.api.dto.errorCatalog;

public class ResidenceErrorCatalog{
    private static final String ERROR_PREFIX = "RESIDENCE-";

    public static final String OWNER_REQUIRED = ERROR_PREFIX + "001";
    public static final String NAME_REQUIRED = ERROR_PREFIX + "002";
    public static final String PROPERTY_TYPE_REQUIRED = ERROR_PREFIX + "003";
    public static final String BEDROOMS_NEGATIVE = ERROR_PREFIX + "004";
    public static final String BATHROOMS_NEGATIVE = ERROR_PREFIX + "005";
    public static final String GARAGE_SPOT_NEGATIVE = ERROR_PREFIX + "006";
    public static final String USABLE_AREA_NEGATIVE = ERROR_PREFIX + "007";
    public static final String TOTAL_AREA_NEGATIVE = ERROR_PREFIX + "008";
    public static final String STATUS_REQUIRED = ERROR_PREFIX + "009";
    public static final String ADDRESS_REQUIRED = ERROR_PREFIX + "009";
    public static final String CITY_NOT_FOUND = ERROR_PREFIX + "010";
    public static final String CANNOT_CREATE_RESIDENCE_FOR_ANOTHER_USER = ERROR_PREFIX + "011";
    public static final String DUPLICATE_RESIDENCE_NAME_FOR_OWNER = ERROR_PREFIX + "012";
    public static final String USABLE_AREA_IS_BIGGER_THAN_TOTAL_AREA = ERROR_PREFIX + "013";
    public static final String TOTAL_AREA_MUST_BE_POSITIVE_FOR_HOUSE = ERROR_PREFIX + "014";
    public static final String USABLE_AREA_MUST_BE_POSITIVE_FOR_APARTMENT = ERROR_PREFIX + "015";
    public static final String BEDROOM_MUST_BE_POSITIVE_FOR_RESIDENTIAL = ERROR_PREFIX + "016";
    public static final String BATHROOM_MUST_BE_POSITIVE_FOR_RESIDENTIAL = ERROR_PREFIX + "016";

}
