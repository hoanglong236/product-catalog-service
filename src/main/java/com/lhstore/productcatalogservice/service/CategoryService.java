package com.lhstore.productcatalogservice.service;

import com.lhstore.productcatalogservice.dto.RequestCategory;
import com.lhstore.productcatalogservice.dto.ResponseCategory;
import com.lhstore.productcatalogservice.exception.ResourceAlreadyExistsException;
import com.lhstore.productcatalogservice.exception.ResourceNotFoundException;
import com.lhstore.productcatalogservice.mapper.CategoryMapper;
import com.lhstore.productcatalogservice.model.Category;
import com.lhstore.productcatalogservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private boolean isCategoryIdExists(Integer categoryId) {
        final Optional<Category> categoryOptional = this.categoryRepository
                .retrieveCategoryById(categoryId);
        return categoryOptional.isPresent();
    }

    private boolean isCategoryNameExists(String categoryName) {
        final Optional<Category> categoryOptional = this.categoryRepository
                .retrieveCategoryByName(categoryName);
        return categoryOptional.isPresent();
    }

    @Transactional
    public void createCategory(RequestCategory requestCategory) {
        if (isCategoryNameExists(requestCategory.getName())) {
            throw new ResourceAlreadyExistsException("Category name already exists");
        }

        final Category category = new Category();
        category.setName(requestCategory.getName());
        category.setIconPath(requestCategory.getIconPath());

        final Integer parentCategoryId = requestCategory.getParentCategoryId();
        if (parentCategoryId != null && !isCategoryIdExists(parentCategoryId)) {
            throw new ResourceNotFoundException("Could not find the parent category");
        }
        category.setParentId(parentCategoryId);

        this.categoryRepository.save(category);
        log.info("Category {} is saved", category.getId());
    }

    @Transactional
    public void updateCategory(Integer categoryId, RequestCategory requestCategory) {
        final Optional<Category> categoryOptionalById = this.categoryRepository.retrieveCategoryById(categoryId);
        if (categoryOptionalById.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the category");
        }

        final Category category = categoryOptionalById.get();
        final String requestCategoryName = requestCategory.getName();
        if (!category.getName().equals(requestCategoryName) && isCategoryNameExists(requestCategoryName)) {
            throw new ResourceAlreadyExistsException("Category name already exists");
        }
        category.setName(requestCategoryName);
        category.setIconPath(requestCategory.getIconPath());

        final Integer parentCategoryId = requestCategory.getParentCategoryId();
        if (parentCategoryId != null && !isCategoryIdExists(parentCategoryId)) {
            throw new ResourceNotFoundException("Could not find the parent category");
        }
        category.setParentId(parentCategoryId);

        this.categoryRepository.save(category);
        log.info("Category {} is updated", categoryId);
    }

    @Transactional
    public void deleteCategory(Integer categoryId) {
        if (!isCategoryIdExists(categoryId)) {
            throw new ResourceNotFoundException("Could not find the category");
        }

        this.categoryRepository.deleteCategoriesRecursive(categoryId);
        log.info("Category {} is deleted", categoryId);
    }

    @Transactional(readOnly = true)
    public Set<ResponseCategory> retrieveCategories() {
        final Set<Category> categories = this.categoryRepository.retrieveCategories();
        return this.categoryMapper.mapToResponseCategories(categories);
    }
}
