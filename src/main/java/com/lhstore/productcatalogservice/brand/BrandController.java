package com.lhstore.productcatalogservice.brand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandService.createBrand(brandRequest);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateBrand(@PathVariable("id") int brandId, @Valid @RequestBody BrandRequest brandRequest) {
        brandService.updateBrand(brandId, brandRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBrand(@PathVariable("id") int brandId) {
        brandService.deleteBrand(brandId);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<BrandResponse> retrieveAllBrands() {
        return brandService.retrieveBrands();
    }
}
