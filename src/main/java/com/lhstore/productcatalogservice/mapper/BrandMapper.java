package com.lhstore.productcatalogservice.mapper;

import com.lhstore.productcatalogservice.dto.ResponseBrand;
import com.lhstore.productcatalogservice.model.Brand;
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
                .iconPath(brand.getIconPath())
                .build();
    }

    public Set<ResponseBrand> mapToResponseBrands(Collection<Brand> brands) {
        return brands.stream()
                .map(this::mapToResponseBrand)
                .collect(Collectors.toSet());
    }
}
