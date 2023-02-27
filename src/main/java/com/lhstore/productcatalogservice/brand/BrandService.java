package com.lhstore.productcatalogservice.brand;

import com.lhstore.productcatalogservice.exceptions.ResourceAlreadyExistsException;
import com.lhstore.productcatalogservice.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Transactional
    public void createBrand(RequestBrand requestBrand) {
        if (brandRepository.isBrandNameExists(requestBrand.getName())) {
            throw new ResourceAlreadyExistsException("Brand name already exists");
        }

        final Brand brand = new Brand();
        brand.setName(requestBrand.getName());
        brand.setLogoPath(requestBrand.getLogoPath());

        brandRepository.save(brand);
        log.info("Brand {} is saved", brand.getId());
    }

    @Transactional
    public void updateBrand(int brandId, RequestBrand requestBrand) {
        final Optional<Brand> brandOptional = brandRepository.retrieveById(brandId);
        if (brandOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the brand");
        }

        final Brand brand = brandOptional.get();
        final String requestBrandName = requestBrand.getName();
        if (!brand.getName().equals(requestBrandName) && brandRepository.isBrandNameExists(requestBrandName)) {
            throw new ResourceAlreadyExistsException("Brand name already exists");
        }
        brand.setName(requestBrandName);
        brand.setLogoPath(requestBrand.getLogoPath());

        brandRepository.save(brand);
        log.info("Brand {} is updated", brandId);
    }

    @Transactional
    public void deleteBrand(int brandId) {
        final Optional<Brand> brandOptional = brandRepository.retrieveById(brandId);
        if (brandOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the brand");
        }

        final Brand brand = brandOptional.get();
        brand.setDeleteFlag(true);

        brandRepository.save(brand);
        log.info("Brand {} is deleted", brandId);
    }

    @Transactional(readOnly = true)
    public Set<ResponseBrand> retrieveBrands() {
        final Set<Brand> brands = brandRepository.retrieveBrands();
        return brandMapper.mapToResponseBrands(brands);
    }
}
