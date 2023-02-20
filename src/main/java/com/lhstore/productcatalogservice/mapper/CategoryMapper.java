package com.lhstore.productcatalogservice.mapper;

import com.lhstore.productcatalogservice.dto.ResponseCategory;
import com.lhstore.productcatalogservice.model.Category;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public ResponseCategory mapToResponseCategory(Category category) {
        return ResponseCategory.builder()
                .id(category.getId())
                .parentCategoryId(category.getParentId())
                .name(category.getName())
                .iconPath(category.getIconPath())
                .build();
    }

    public Set<ResponseCategory> mapToResponseCategories(Collection<Category> categories) {
        return categories.stream()
                .map(this::mapToResponseCategory)
                .collect(Collectors.toSet());
    }
}
