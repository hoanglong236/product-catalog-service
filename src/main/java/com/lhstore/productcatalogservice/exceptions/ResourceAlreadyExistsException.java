package com.lhstore.productcatalogservice.exceptions;

public class ResourceAlreadyExistsException extends GlobalException {

    public ResourceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
