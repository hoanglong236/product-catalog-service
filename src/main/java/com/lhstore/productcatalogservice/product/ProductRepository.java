package com.lhstore.productcatalogservice.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.deleteFlag = FALSE AND p.id = :id")
    Optional<Product> retrieveById(@Param("id") long productId);

    @Modifying
    @Query("UPDATE Product p SET p.deleteFlag = TRUE WHERE p.id = :id")
    void customDeleteById(@Param("id") long productId);

    @Query("SELECT p FROM Product p WHERE p.deleteFlag = FALSE")
    Set<Product> retrieveProducts();

    @Query("SELECT p FROM Product p WHERE p.deleteFlag = FALSE AND p.categoryId = :categoryId")
    Set<Product> retrieveProductsByCategoryId(@Param("categoryId") int categoryId);

    @Query("SELECT p FROM Product p WHERE p.deleteFlag = FALSE AND p.brandId = :brandId")
    Set<Product> retrieveProductsByBrandId(@Param("brandId") int brandId);

    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Product p WHERE p.deleteFlag = FALSE AND p.id = :id")
    boolean isProductIdExists(@Param("id") long productId);
}
