package com.lhstore.productcatalogservice.productspecification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/product-specification")
@RequiredArgsConstructor
public class ProductSpecificationController {

    private final ProductSpecificationService productSpecificationService;

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProductSpecification(
            @Valid @RequestBody RequestProductSpecification requestProductSpecification) {

        productSpecificationService.createProductSpecification(requestProductSpecification);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProductSpecification(@PathVariable("id") long productImageId) {
        productSpecificationService.deleteProductSpecification(productImageId);
    }

    @GetMapping(value = "/list-by-product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseProductSpecification> retrieveAllSpecificationsByProduct(
            @PathVariable("id") long productId) {

        return productSpecificationService.retrieveProductSpecificationsByProduct(productId);
    }
}
