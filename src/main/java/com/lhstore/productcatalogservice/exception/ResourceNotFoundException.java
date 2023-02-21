package com.lhstore.productcatalogservice.exception;

public class ResourceNotFoundException extends GlobalException {

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
