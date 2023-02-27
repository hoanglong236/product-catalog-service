package com.lhstore.productcatalogservice.brand;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BrandMapper {

    public ResponseBrand mapToResponseBrand(Brand brand) {
        return ResponseBrand.builder()
                .id(brand.getId())
                .name(brand.getName())
                .logoPath(brand.getLogoPath())
                .build();
    }

    public Set<ResponseBrand> mapToResponseBrands(Collection<Brand> brands) {
        return brands.stream()
                .map(this::mapToResponseBrand)
                .collect(Collectors.toSet());
    }
}
