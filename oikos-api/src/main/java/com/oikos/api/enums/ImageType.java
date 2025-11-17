package com.oikos.api.enums;

import lombok.Getter;

@Getter
public enum ImageType {
    JPEG("image/jpeg"),
    PNG("image/png"),
    WEBP("image/webp");

    private final String mime;

    ImageType(String mime) {
        this.mime = mime;
    }
}
