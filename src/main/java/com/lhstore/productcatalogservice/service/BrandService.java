package com.lhstore.productcatalogservice.service;

import com.lhstore.productcatalogservice.dto.RequestBrand;
import com.lhstore.productcatalogservice.dto.ResponseBrand;
import com.lhstore.productcatalogservice.exception.ResourceAlreadyExistsException;
import com.lhstore.productcatalogservice.exception.ResourceNotFoundException;
import com.lhstore.productcatalogservice.mapper.BrandMapper;
import com.lhstore.productcatalogservice.model.Brand;
import com.lhstore.productcatalogservice.repository.BrandRepository;
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

    private boolean isBrandIdExists(Integer brandId) {
        final Optional<Brand> brandOptional = this.brandRepository.retrieveBrandById(brandId);
        return brandOptional.isPresent();
    }

    private boolean isBrandNameExists(String brandName) {
        final Optional<Brand> brandOptional = this.brandRepository.retrieveBrandByName(brandName);
        return brandOptional.isPresent();
    }

    @Transactional
    public void createBrand(RequestBrand requestBrand) {
        if (isBrandNameExists(requestBrand.getName())) {
            throw new ResourceAlreadyExistsException("Brand name already exists");
        }

        final Brand brand = new Brand();
        brand.setName(requestBrand.getName());
        brand.setIconPath(requestBrand.getIconPath());

        this.brandRepository.save(brand);
        log.info("Brand {} is saved", brand.getId());
    }

    @Transactional
    public void updateBrand(Integer brandId, RequestBrand requestBrand) {
        final Optional<Brand> brandOptional = this.brandRepository.retrieveBrandById(brandId);
        if (brandOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the brand");
        }

        final Brand brand = brandOptional.get();
        final String requestBrandName = requestBrand.getName();
        if (!brand.getName().equals(requestBrandName) && isBrandNameExists(requestBrandName)) {
            throw new ResourceAlreadyExistsException("Brand name already exists");
        }
        brand.setName(requestBrandName);
        brand.setIconPath(requestBrand.getIconPath());

        this.brandRepository.save(brand);
        log.info("Brand {} is updated", brandId);
    }

    @Transactional
    public void deleteBrand(Integer brandId) {
        if (!isBrandIdExists(brandId)) {
            throw new ResourceNotFoundException("Could not find the brand");
        }

        this.brandRepository.deleteBrandById(brandId);
        log.info("Brand {} is deleted", brandId);
    }

    @Transactional(readOnly = true)
    public Set<ResponseBrand> retrieveBrands() {
        final Set<Brand> brands = this.brandRepository.retrieveBrands();
        return this.brandMapper.mapToResponseBrands(brands);
    }
}
