package com.lhstore.productcatalogservice.product;

import com.lhstore.productcatalogservice.exceptions.ResourceNotFoundException;
import com.lhstore.productcatalogservice.brand.BrandRepository;
import com.lhstore.productcatalogservice.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void createProduct(RequestProduct requestProduct) {
        final Product product = new Product();
        product.setName(requestProduct.getName());

        if (!categoryRepository.isCategoryIdExists(requestProduct.getCategoryId())) {
            throw new ResourceNotFoundException("Could not find the category");
        }
        product.setCategoryId(requestProduct.getCategoryId());

        if (!brandRepository.isBrandIdExists(requestProduct.getBrandId())) {
            throw new ResourceNotFoundException("Could not find the brand");
        }
        product.setBrandId(requestProduct.getBrandId());

        product.setMainImagePath(requestProduct.getMainImagePath());
        product.setPrice(requestProduct.getPrice());
        product.setQuantity(requestProduct.getQuantity());
        product.setDescriptionFilePath(requestProduct.getDescriptionFilePath());
        product.setProductStatus(requestProduct.getProductStatus());

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    @Transactional
    public void updateProduct(long productId, RequestProduct requestProduct) {
        final Optional<Product> productOptional = productRepository.retrieveById(productId);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the product");
        }

        final Product product = productOptional.get();
        product.setName(requestProduct.getName());

        if (!categoryRepository.isCategoryIdExists(requestProduct.getCategoryId())) {
            throw new ResourceNotFoundException("Could not find the category");
        }
        product.setCategoryId(requestProduct.getCategoryId());

        if (!brandRepository.isBrandIdExists(requestProduct.getBrandId())) {
            throw new ResourceNotFoundException("Could not find the brand");
        }
        product.setBrandId(requestProduct.getBrandId());

        product.setMainImagePath(requestProduct.getMainImagePath());
        product.setPrice(requestProduct.getPrice());
        product.setQuantity(requestProduct.getQuantity());
        product.setDescriptionFilePath(requestProduct.getDescriptionFilePath());
        product.setProductStatus(requestProduct.getProductStatus());

        productRepository.save(product);
        log.info("Product {} is updated", product.getId());
    }

    @Transactional
    public void deleteProduct(long productId) {
        if (!productRepository.isProductIdExists(productId)) {
            throw new ResourceNotFoundException("Could not find the product");
        }

        productRepository.customDeleteById(productId);
        log.info("Product {} is deleted", productId);
    }

    @Transactional(readOnly = true)
    public Set<ResponseProduct> retrieveProducts() {
        final Set<Product> products = productRepository.retrieveProducts();
        return productMapper.mapToResponseProducts(products);
    }

    @Transactional(readOnly = true)
    public Set<ResponseProduct> retrieveProductsByCategory(int categoryId) {
        final Set<Product> products = productRepository.retrieveProductsByCategoryId(categoryId);
        return productMapper.mapToResponseProducts(products);
    }

    @Transactional(readOnly = true)
    public Set<ResponseProduct> retrieveProductsByBrand(int brandId) {
        final Set<Product> products = productRepository.retrieveProductsByBrandId(brandId);
        return productMapper.mapToResponseProducts(products);
    }

    @Transactional(readOnly = true)
    public Set<ResponseProduct> searchProducts(Map<String, String> searchOptions) {
        return new LinkedHashSet<>();
    }
}
