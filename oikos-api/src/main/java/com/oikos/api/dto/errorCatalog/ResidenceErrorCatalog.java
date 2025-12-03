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
    public static final String CITY_NOT_FOUND = ERROR_PREFIX + "010";;

}
