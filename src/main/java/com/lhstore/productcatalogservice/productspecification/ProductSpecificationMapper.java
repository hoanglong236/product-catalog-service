package com.lhstore.productcatalogservice.productspecification;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductSpecificationMapper {

    public ResponseProductSpecification mapToResponseProductSpecification(
            ProductSpecification productSpecification) {

        return ResponseProductSpecification.builder()
                .id(productSpecification.getId())
                .productId(productSpecification.getProductId())
                .specificationName(productSpecification.getSpecificationName())
                .specificationValue(productSpecification.getSpecificationValue())
                .build();
    }

    public Set<ResponseProductSpecification> mapToResponseProductSpecifications(
            Collection<ProductSpecification> productSpecifications) {

        return productSpecifications.stream()
                .map(this::mapToResponseProductSpecification)
                .collect(Collectors.toSet());
    }
}
