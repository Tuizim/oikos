package com.oikos.api.exceptions;

public class OikosErrorCatalog {
    private static final String OIKOS_PREFIX = "OIKOS-";
    
    public static final String GENERIC = OIKOS_PREFIX + "000";
    public static final String USUARIO_NAO_ENCONTRADO = OIKOS_PREFIX + "001";
    public static final String USUARIO_LOGIN_JA_REGISTRADO = OIKOS_PREFIX + "002";
    public static final String ERRO_AO_CRIAR_TOKEN = OIKOS_PREFIX + "003";
}
