package com.lhstore.productcatalogservice.productspecification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(ps) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ProductSpecification ps " +
            "WHERE ps.productId = :productId AND specificationName = :specificationName")
    boolean isSpecificationNameExistsInProduct(
            @Param("productId") long productId, @Param("specificationName") String specificationName);

    @Query("SELECT ps FROM ProductSpecification ps WHERE ps.productId = :productId")
    Set<ProductSpecification> retrieveProductSpecificationsByProductId(@Param("productId") long productId);
}
