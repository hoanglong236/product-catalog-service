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
    public void createBrand(BrandRequest brandRequest) {
        if (brandRepository.isBrandNameExists(brandRequest.getName())) {
            throw new ResourceAlreadyExistsException("Brand name already exists");
        }

        final Brand brand = new Brand();
        brand.setName(brandRequest.getName());
        brand.setLogoPath(brandRequest.getLogoPath());
        brand.setDeleteFlag(false);

        brandRepository.save(brand);
        log.info("Brand {} is saved", brand.getId());
    }

    @Transactional
    public void updateBrand(int brandId, BrandRequest brandRequest) {
        final Optional<Brand> brandOptional = brandRepository.retrieveById(brandId);
        if (brandOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the brand");
        }

        final Brand brand = brandOptional.get();
        final String brandRequestName = brandRequest.getName();
        if (!brand.getName().equals(brandRequestName) && brandRepository.isBrandNameExists(brandRequestName)) {
            throw new ResourceAlreadyExistsException("Brand name already exists");
        }
        brand.setName(brandRequestName);
        brand.setLogoPath(brandRequest.getLogoPath());

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
    public Set<BrandResponse> retrieveBrands() {
        final Set<Brand> brands = brandRepository.retrieveBrands();
        return brandMapper.mapToBrandResponses(brands);
    }
}
