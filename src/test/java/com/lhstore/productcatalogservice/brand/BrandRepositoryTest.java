package com.lhstore.productcatalogservice.brand;

import com.lhstore.productcatalogservice.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BrandRepositoryTest extends AbstractTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void contextLoads() {
        assertThat(brandRepository).isNotNull();
    }

    private Brand givenDeletedBrand() {
        final Brand brand = new Brand();
        brand.setName("Test brand 1");
        brand.setLogoPath("C://test/logo-test.png");
        brand.setDeleteFlag(true);

        brandRepository.save(brand);
        return brand;
    }

    private Brand givenBrand() {
        final Brand brand = new Brand();
        brand.setName("Test brand 2");
        brand.setLogoPath("C://test/logo-test.png");
        brand.setDeleteFlag(false);

        brandRepository.save(brand);
        return brand;
    }

    @Test
    void givenBrands_whenRetrieveWithNotExistsBrandId_thenReturnEmptyOptional() {
        givenDeletedBrand();
        givenBrand();

        final int notExistsBrandId = -999;
        assertThat(brandRepository.retrieveById(notExistsBrandId)).isEmpty();
    }

    @Test
    void givenBrand_whenRetrieveWithExistsBrandId_thenReturnBrandOptional() {
        final Brand brand = givenBrand();
        assertThat(brandRepository.retrieveById(brand.getId())).hasValue(brand);
    }

    @Test
    void givenBrand_whenRetrieveWithDeletedBrandId_thenReturnEmptyOptional() {
        final Brand deletedBrand = givenDeletedBrand();
        assertThat(brandRepository.retrieveById(deletedBrand.getId())).isEmpty();
    }

    @Test
    void givenBrands_whenRetrieveBrands_thenReturnBrandsThatNotIncludesDeletedBrands() {
        final Brand deletedBrand = givenDeletedBrand();
        final Brand brand = givenBrand();

        final Set<Brand> resultBrands = brandRepository.retrieveBrands();

        assertThat(resultBrands.size()).isEqualTo(1);
        assertThat(resultBrands.contains(brand)).isTrue();
        assertThat(resultBrands.contains(deletedBrand)).isFalse();
    }

    @Test
    void givenBrands_whenCheckBrandIdExistsWithNotExistsBrandId_thenReturnFalse() {
        givenDeletedBrand();
        givenBrand();

        final int notExistsBrandId = -999;
        assertThat(brandRepository.isBrandIdExists(notExistsBrandId)).isFalse();
    }

    @Test
    void givenBrand_whenCheckBrandIdExistsWithExistsBrandId_thenReturnTrue() {
        final Brand brand = givenBrand();
        assertThat(brandRepository.isBrandIdExists(brand.getId())).isTrue();
    }

    @Test
    void givenBrand_whenCheckBrandIdExistsWithDeletedBrandId_thenReturnFalse() {
        final Brand deletedBrand = givenDeletedBrand();
        assertThat(brandRepository.isBrandIdExists(deletedBrand.getId())).isFalse();
    }

    @Test
    void givenBrands_whenCheckBrandNameExistsWithNotExistsBrandName_thenReturnFalse() {
        givenDeletedBrand();
        givenBrand();

        final String notExistsBrandName = "Test brand 3";
        assertThat(brandRepository.isBrandNameExists(notExistsBrandName)).isFalse();
    }

    @Test
    void givenBrand_whenCheckBrandNameExistsWithExistsBrandName_thenReturnTrue() {
        final Brand brand = givenBrand();
        assertThat(brandRepository.isBrandNameExists(brand.getName())).isTrue();
    }

    @Test
    void givenBrand_whenCheckBrandNameExistsWithDeletedBrandName_thenReturnFalse() {
        final Brand deletedBrand = givenDeletedBrand();
        assertThat(brandRepository.isBrandNameExists(deletedBrand.getName())).isFalse();
    }
}