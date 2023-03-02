package com.lhstore.productcatalogservice.category;

import com.lhstore.productcatalogservice.exceptions.ResourceAlreadyExistsException;
import com.lhstore.productcatalogservice.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public void createCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.isCategoryNameExists(categoryRequest.getName())) {
            throw new ResourceAlreadyExistsException("Category name already exists");
        }
        final Integer categoryRequestParentId = categoryRequest.getParentId();
        if (categoryRequestParentId != null && !categoryRepository.isCategoryIdExists(categoryRequestParentId)) {
            throw new ResourceNotFoundException("Could not find the parent category");
        }

        final Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setIconPath(categoryRequest.getIconPath());
        category.setParentId(categoryRequestParentId);

        categoryRepository.save(category);
        log.info("Category {} is saved", category.getId());
    }

    @Transactional
    public void updateCategory(int categoryId, CategoryRequest categoryRequest) {
        final Optional<Category> categoryOptionalById = this.categoryRepository.retrieveById(categoryId);
        if (categoryOptionalById.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the category");
        }
        if (categoryRepository.isCategoryNameExistsInOtherCategories(categoryRequest.getName(), categoryId)) {
            throw new ResourceAlreadyExistsException("Category name already exists");
        }
        final Integer categoryRequestParentId = categoryRequest.getParentId();
        if (categoryRequestParentId != null && !categoryRepository.isCategoryIdExists(categoryRequestParentId)) {
            throw new ResourceNotFoundException("Could not find the parent category");
        }

        final Category category = categoryOptionalById.get();
        category.setName(categoryRequest.getName());
        category.setIconPath(categoryRequest.getIconPath());
        category.setParentId(categoryRequestParentId);

        categoryRepository.save(category);
        log.info("Category {} is updated", categoryId);
    }

    @Transactional
    public void deleteCategory(int categoryId) {
        if (!categoryRepository.isCategoryIdExists(categoryId)) {
            throw new ResourceNotFoundException("Could not find the category");
        }

        categoryRepository.deleteCategoryTree(categoryId);
        log.info("Category {} is deleted", categoryId);
    }

    @Transactional(readOnly = true)
    public Set<CategoryResponse> retrieveCategories() {
        final Set<Category> categories = categoryRepository.retrieveCategories();
        return categories.stream().map(categoryMapper::categoryToCategoryResponse).collect(Collectors.toSet());
    }
}
