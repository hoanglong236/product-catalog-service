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

    private Brand createSetupDeleteBrand() {
        final Brand deleteBrand = new Brand();
        deleteBrand.setName("Test brand 1");
        deleteBrand.setLogoPath("C://test/logo-test.png");
        deleteBrand.setDeleteFlag(true);

        brandRepository.save(deleteBrand);
        return deleteBrand;
    }

    private Brand createSetupUndeleteBrand() {
        final Brand undeleteBrand = new Brand();
        undeleteBrand.setName("Test brand 2");
        undeleteBrand.setLogoPath("C://test/logo-test.png");

        brandRepository.save(undeleteBrand);
        return undeleteBrand;
    }

    @Test
    void givenBrands_whenRetrieveWithNotExistsBrandId_thenReturnEmptyOptional() {
        final Brand deleteBrand = createSetupDeleteBrand();
        final Brand undeleteBrand = createSetupUndeleteBrand();

        final int notExistsBrandId = -999;
        assertThat(brandRepository.retrieveById(notExistsBrandId)).isEmpty();
    }

    @Test
    void givenBrand_whenRetrieveWithDeleteBrandId_thenReturnEmptyOptional() {
        final Brand deleteBrand = createSetupDeleteBrand();
        assertThat(brandRepository.retrieveById(deleteBrand.getId())).isEmpty();
    }

    @Test
    void givenBrand_whenRetrieveWithUndeleteBrandId_thenReturnBrandOptional() {
        final Brand undeleteBrand = createSetupUndeleteBrand();
        assertThat(brandRepository.retrieveById(undeleteBrand.getId())).hasValue(undeleteBrand);
    }

    @Test
    void givenBrands_whenRetrieveBrands_thenReturnUndeleteBrands() {
        final Brand deleteBrand = createSetupDeleteBrand();
        final Brand undeleteBrand = createSetupUndeleteBrand();

        final Set<Brand> resultBrands = brandRepository.retrieveBrands();

        assertThat(resultBrands.size()).isEqualTo(1);
        assertThat(resultBrands.contains(undeleteBrand)).isTrue();
        assertThat(resultBrands.contains(deleteBrand)).isFalse();
    }

    @Test
    void givenBrands_whenCheckBrandIdExistsWithNotExistsBrandId_thenReturnFalse() {
        final Brand deleteBrand = createSetupDeleteBrand();
        final Brand undeleteBrand = createSetupUndeleteBrand();

        final int notExistsBrandId = -999;
        assertThat(brandRepository.isBrandIdExists(notExistsBrandId)).isFalse();
    }

    @Test
    void givenBrand_whenCheckBrandIdExistsWithDeleteBrandId_thenReturnFalse() {
        final Brand deleteBrand = createSetupDeleteBrand();
        assertThat(brandRepository.isBrandIdExists(deleteBrand.getId())).isFalse();
    }

    @Test
    void givenBrand_whenCheckBrandIdExistsWithUndeleteBrandId_thenReturnTrue() {
        final Brand undeleteBrand = createSetupUndeleteBrand();
        assertThat(brandRepository.isBrandIdExists(undeleteBrand.getId())).isTrue();
    }

    @Test
    void givenBrands_whenCheckBrandNameExistsWithNotExistsBrandName_thenReturnFalse() {
        final Brand deleteBrand = createSetupDeleteBrand();
        final Brand undeleteBrand = createSetupUndeleteBrand();

        final String notExistsBrandName = "Test brand 3";
        assertThat(brandRepository.isBrandNameExists(notExistsBrandName)).isFalse();
    }

    @Test
    void givenBrand_whenCheckBrandNameExistsWithDeleteBrandName_thenReturnFalse() {
        final Brand deleteBrand = createSetupDeleteBrand();
        assertThat(brandRepository.isBrandNameExists(deleteBrand.getName())).isFalse();
    }

    @Test
    void givenBrand_whenCheckBrandNameExistsWithUndeleteBrandName_thenReturnTrue() {
        final Brand undeleteBrand = createSetupUndeleteBrand();
        assertThat(brandRepository.isBrandNameExists(undeleteBrand.getName())).isTrue();
    }
}