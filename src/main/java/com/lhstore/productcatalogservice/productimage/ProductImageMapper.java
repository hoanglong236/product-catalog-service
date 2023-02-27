package com.lhstore.productcatalogservice.productimage;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductImageMapper {

    public ResponseProductImage mapToResponseProductImage(ProductImage productImage) {
        return ResponseProductImage.builder()
                .id(productImage.getId())
                .productId(productImage.getProductId())
                .imagePath(productImage.getImagePath())
                .build();
    }

    public Set<ResponseProductImage> mapToResponseProductImages(Collection<ProductImage> productImages) {
        return productImages.stream()
                .map(this::mapToResponseProductImage)
                .collect(Collectors.toSet());
    }
}
