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

    @Test
    void givenBrands_whenBrandIdNotExists_thenReturnEmptyOptional() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(deleteBrand);
        brandRepository.save(undeleteBrand);

        final int notExistsBrandId = -999;
        assertThat(brandRepository.retrieveById(notExistsBrandId)).isEmpty();
    }

    @Test
    void givenDeleteBrand_whenBrandIdIsDeleteBrandId_thenReturnEmptyOptional() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        brandRepository.save(deleteBrand);

        assertThat(brandRepository.retrieveById(deleteBrand.getId())).isEmpty();
    }

    @Test
    void givenUndeleteBrand_whenBrandIdIsUndeleteBrandId_thenReturnBrandOptional() {
        final Brand brand = new Brand();
        brand.setName("Test brand 1");
        brand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(brand);

        assertThat(brandRepository.retrieveById(brand.getId())).hasValue(brand);
    }

    @Test
    void givenBrands_thenReturnUndeleteBrands() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(deleteBrand);
        brandRepository.save(undeleteBrand);

        final Set<Brand> resultBrands = brandRepository.retrieveBrands();
        assertThat(resultBrands.size()).isEqualTo(1);
        assertThat(resultBrands.contains(undeleteBrand)).isTrue();
        assertThat(resultBrands.contains(deleteBrand)).isFalse();
    }

    @Test
    void givenBrands_whenBrandIdNotExists_thenReturnFalse() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(deleteBrand);
        brandRepository.save(undeleteBrand);

        final int notExistsBrandId = -999;
        assertThat(brandRepository.isBrandIdExists(notExistsBrandId)).isFalse();
    }

    @Test
    void givenDeleteBrand_whenBrandIdIsDeleteBrandId_thenReturnFalse() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        brandRepository.save(deleteBrand);

        assertThat(brandRepository.isBrandIdExists(deleteBrand.getId())).isFalse();
    }

    @Test
    void givenUndeleteBrand_whenBrandIdIsUndeleteBrandId_thenReturnTrue() {
        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(undeleteBrand);

        assertThat(brandRepository.isBrandIdExists(undeleteBrand.getId())).isTrue();
    }

    @Test
    void givenBrands_whenBrandNameNotExists_thenReturnFalse() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(deleteBrand);
        brandRepository.save(undeleteBrand);

        final String notExistsBrandName = "Test brand 3";
        assertThat(brandRepository.isBrandNameExists(notExistsBrandName)).isFalse();
    }

    @Test
    void givenDeleteBrand_whenBrandNameIsDeleteBrandName_thenReturnFalse() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        brandRepository.save(deleteBrand);

        assertThat(brandRepository.isBrandNameExists(deleteBrand.getName())).isFalse();
    }

    @Test
    void givenUndeleteBrand_whenBrandNameIsUndeleteBrandName_thenReturnTrue() {
        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(undeleteBrand);

        assertThat(brandRepository.isBrandNameExists(undeleteBrand.getName())).isTrue();
    }
}