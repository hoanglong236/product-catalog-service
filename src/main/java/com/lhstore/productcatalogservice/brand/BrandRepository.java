package com.lhstore.productcatalogservice.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("SELECT b FROM Brand b WHERE b.deleteFlag = FALSE AND b.id = :id")
    Optional<Brand> retrieveById(@Param("id") int brandId);

    @Query("SELECT b FROM Brand b WHERE b.deleteFlag = FALSE")
    Set<Brand> retrieveBrands();

    @Query("" +
            "SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Brand b WHERE b.deleteFlag = FALSE AND b.id = :id")
    boolean isBrandIdExists(@Param("id") int brandId);

    @Query("" +
            "SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Brand b WHERE b.deleteFlag = FALSE AND b.name = :name")
    boolean isBrandNameExists(@Param("name") String brandName);

    @Query("" +
            "SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Brand b WHERE b.deleteFlag = FALSE AND b.name = :name AND b.id != :id")
    boolean isBrandNameExistsInOtherBrands(@Param("name") String brandName, @Param("id") int exceptBrandId);
}
