package com.lhstore.productcatalogservice.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse categoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .parentId(category.getParentId())
                .name(category.getName())
                .iconPath(category.getIconPath())
                .build();
    }
}
