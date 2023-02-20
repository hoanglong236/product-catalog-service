package com.lhstore.productcatalogservice.repository;

public interface CustomCategoryRepository {

    void deleteCategoriesRecursive(Integer categoryId);
}
