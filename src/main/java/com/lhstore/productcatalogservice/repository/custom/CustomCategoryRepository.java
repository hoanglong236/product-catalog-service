package com.lhstore.productcatalogservice.repository.custom;

public interface CustomCategoryRepository {

    void deleteCategoriesRecursive(Integer categoryId);
}
