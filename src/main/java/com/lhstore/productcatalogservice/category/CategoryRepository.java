package com.lhstore.productcatalogservice.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {

    @Query("SELECT c FROM Category c WHERE c.deleteFlag = FALSE AND c.id = :id")
    Optional<Category> retrieveById(@Param("id") int categoryId);

    @Query("SELECT c FROM Category c WHERE c.deleteFlag = FALSE")
    Set<Category> retrieveCategories();

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Category c WHERE c.deleteFlag = FALSE AND c.id = :id")
    boolean isCategoryIdExists(@Param("id") int categoryId);

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Category c WHERE c.deleteFlag = FALSE AND c.name = :name")
    boolean isCategoryNameExists(@Param("name") String categoryName);

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Category c WHERE c.deleteFlag = FALSE AND c.name = :name AND c.id != :id")
    boolean isCategoryNameExistsInOtherCategories(
            @Param("name") String categoryName, @Param("id") int exceptCategoryId);
}
