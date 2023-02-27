package com.lhstore.productcatalogservice.productimage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/product-image")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProductImage(@Valid @RequestBody RequestProductImage requestProductImage) {
        productImageService.createProductImage(requestProductImage);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProductImage(@PathVariable("id") long productImageId) {
        productImageService.deleteProductImage(productImageId);
    }

    @GetMapping(value = "/list-by-product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseProductImage> retrieveAllProductImagesByProduct(@PathVariable("id") long productId) {
        return productImageService.retrieveProductImagesByProduct(productId);
    }
}
