package com.lhstore.productcatalogservice.repository;

import com.lhstore.productcatalogservice.model.Category;
import com.lhstore.productcatalogservice.repository.sql.CategorySql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CustomCategoryRepository {

    @Query("SELECT c FROM Category c WHERE c.deletedFlag = FALSE")
    Set<Category> retrieveCategories();

    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.deletedFlag = FALSE ")
    Optional<Category> retrieveCategoryById(@Param("id") Integer categoryId);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.deletedFlag = FALSE ")
    Optional<Category> retrieveCategoryByName(@Param("name") String categoryName);
}
