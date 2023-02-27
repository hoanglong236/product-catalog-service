package com.lhstore.productcatalogservice.exceptions;

public class ResourceNotFoundException extends GlobalException {

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
