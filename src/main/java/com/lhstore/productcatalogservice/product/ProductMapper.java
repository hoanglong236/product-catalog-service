package com.lhstore.productcatalogservice.product;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ResponseProduct mapToResponseProduct(Product product) {
        return ResponseProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .brandId(product.getBrandId())
                .mainImagePath(product.getMainImagePath())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .descriptionFilePath(product.getDescriptionFilePath())
                .productStatus(product.getProductStatus())
                .build();
    }

    public Set<ResponseProduct> mapToResponseProducts(Collection<Product> products) {
        return products.stream()
                .map(this::mapToResponseProduct)
                .collect(Collectors.toSet());
    }
}
