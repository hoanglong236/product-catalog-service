package com.lhstore.productcatalogservice.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody RequestProduct requestProduct) {
        productService.createProduct(requestProduct);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProduct(@PathVariable("id") long productId,
            @Valid @RequestBody RequestProduct requestProduct) {

        productService.updateProduct(productId, requestProduct);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseProduct> retrieveAllProducts() {
        return productService.retrieveProducts();
    }

    @GetMapping(value = "/list-by-category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseProduct> retrieveAllProductsByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.retrieveProductsByCategory(categoryId);
    }

    @GetMapping(value = "/list-by-brand/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseProduct> retrieveAllProductsByBrand(@PathVariable("brandId") int brandId) {
        return productService.retrieveProductsByBrand(brandId);
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseProduct> searchProducts(@RequestParam Map<String, String> searchOptions) {
        return productService.searchProducts(searchOptions);
    }
}
