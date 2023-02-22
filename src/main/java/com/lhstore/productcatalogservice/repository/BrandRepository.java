package com.lhstore.productcatalogservice.repository;

import com.lhstore.productcatalogservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("SELECT b FROM Brand b WHERE b.deletedFlag = FALSE")
    Set<Brand> retrieveBrands();

    @Query("SELECT b FROM Brand b WHERE b.id = :id AND b.deletedFlag = FALSE ")
    Optional<Brand> retrieveBrandById(@Param("id") Integer brandId);

    @Query("SELECT b FROM Brand b WHERE b.name = :name AND b.deletedFlag = FALSE ")
    Optional<Brand> retrieveBrandByName(@Param("name") String brandName);

    @Modifying
    @Query("UPDATE Brand b SET b.deletedFlag = TRUE WHERE b.id = :id")
    void deleteBrandById(@Param("id") Integer brandId);
}
