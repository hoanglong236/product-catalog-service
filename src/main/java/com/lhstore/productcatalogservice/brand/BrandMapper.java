package com.lhstore.productcatalogservice.brand;

import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandResponse brandToBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .logoPath(brand.getLogoPath())
                .build();
    }
}
