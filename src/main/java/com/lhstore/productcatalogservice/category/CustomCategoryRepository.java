package com.lhstore.productcatalogservice.category;

public interface CustomCategoryRepository {

    void deleteCategoryTree(int rootCategoryId);
}
