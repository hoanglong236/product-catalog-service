package com.lhstore.productcatalogservice.brand;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BrandMapper {

    public BrandResponse mapToBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .logoPath(brand.getLogoPath())
                .build();
    }

    public Set<BrandResponse> mapToBrandResponses(Collection<Brand> brands) {
        return brands.stream()
                .map(this::mapToBrandResponse)
                .collect(Collectors.toSet());
    }
}
