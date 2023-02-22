package com.lhstore.productcatalogservice.controller;

import com.lhstore.productcatalogservice.dto.RequestBrand;
import com.lhstore.productcatalogservice.dto.ResponseBrand;
import com.lhstore.productcatalogservice.service.BrandService;
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
    public void createBrand(@Valid @RequestBody RequestBrand requestBrand) {
        this.brandService.createBrand(requestBrand);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateBrand(@PathVariable("id") Integer brandId,
                               @Valid @RequestBody RequestBrand requestBrand) {
        this.brandService.updateBrand(brandId, requestBrand);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBrand(@PathVariable("id") Integer brandId) {
        this.brandService.deleteBrand(brandId);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseBrand> retrieveAllBrands() {
        return this.brandService.retrieveBrands();
    }
}
