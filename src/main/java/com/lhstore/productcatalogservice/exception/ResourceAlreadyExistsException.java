package com.lhstore.productcatalogservice.exception;

public class ResourceAlreadyExistsException extends GlobalException {

    public ResourceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
