package com.lhstore.productcatalogservice.productspecification;

import com.lhstore.productcatalogservice.exceptions.ResourceAlreadyExistsException;
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
public class ProductSpecificationService {

    private final ProductRepository productRepository;
    private final ProductSpecificationRepository productSpecificationRepository;
    private final ProductSpecificationMapper productSpecificationMapper;

    @Transactional
    public void createProductSpecification(RequestProductSpecification requestProductSpecification) {
        final long productId = requestProductSpecification.getProductId();
        if (!productRepository.isProductIdExists(productId)) {
            throw new ResourceNotFoundException("Could not find the product");
        }

        final ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setProductId(productId);

        final String specificationName = requestProductSpecification.getSpecificationName();
        if (productSpecificationRepository.isSpecificationNameExistsInProduct(productId, specificationName)){
            throw new ResourceAlreadyExistsException("Specification key already exists");
        }
        productSpecification.setSpecificationName(requestProductSpecification.getSpecificationName());
        productSpecification.setSpecificationValue(requestProductSpecification.getSpecificationValue());

        productSpecificationRepository.save(productSpecification);
        log.info("Product {} is saved", productSpecification.getId());
    }

    @Transactional
    public void deleteProductSpecification(long productSpecificationId) {
        if (!productSpecificationRepository.existsById(productSpecificationId)) {
            throw new ResourceNotFoundException("Could not find the product specification");
        }

        productSpecificationRepository.deleteById(productSpecificationId);
        log.info("Product specification {} is deleted", productSpecificationId);
    }

    public Set<ResponseProductSpecification> retrieveProductSpecificationsByProduct(long productId) {
        final Set<ProductSpecification> productSpecifications = productSpecificationRepository
                .retrieveProductSpecificationsByProductId(productId);
        return productSpecificationMapper.mapToResponseProductSpecifications(productSpecifications);
    }
}
