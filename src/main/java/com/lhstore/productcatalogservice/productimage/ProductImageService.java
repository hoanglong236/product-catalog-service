package com.lhstore.productcatalogservice.productimage;

import com.lhstore.productcatalogservice.exceptions.ResourceNotFoundException;
import com.lhstore.productcatalogservice.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImageService {

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;

    @Transactional
    public void createProductImage(RequestProductImage requestProductImage) {
        if (!productRepository.isProductIdExists(requestProductImage.getProductId())) {
            throw new ResourceNotFoundException("Could not find the product");
        }

        final ProductImage productImage = new ProductImage();
        productImage.setProductId(requestProductImage.getProductId());
        productImage.setImagePath(requestProductImage.getImagePath());

        productImageRepository.save(productImage);
        log.info("Product image {} is saved", productImage.getId());
    }

    @Transactional
    public void deleteProductImage(long productImageId) {
        if (!productImageRepository.existsById(productImageId)) {
            throw new ResourceNotFoundException("Could not find the product image");
        }

        productImageRepository.deleteById(productImageId);
        log.info("Product image {} is deleted", productImageId);
    }

    @Transactional(readOnly = true)
    public Set<ResponseProductImage> retrieveProductImagesByProduct(long productId) {
        final Set<ProductImage> productImages = productImageRepository.retrieveProductImagesByProductId(productId);
        return productImageMapper.mapToResponseProductImages(productImages);
    }
}
