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

    @Transactional
    public void createCategory(RequestCategory requestCategory) {
        final Optional<Category> categoryOptional = this.categoryRepository
                .retrieveCategoryByName(requestCategory.getName());
        if (categoryOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Category already exists");
        }

        final Category category = new Category();
        category.setName(requestCategory.getName());
        category.setIconPath(requestCategory.getIconPath());

        final Integer parentCategoryId = requestCategory.getParentCategoryId();
        if (parentCategoryId != null) {
            final Optional<Category> parentCategoryOptional = this.categoryRepository
                    .retrieveCategoryById(parentCategoryId);
            if (parentCategoryOptional.isEmpty()) {
                throw new ResourceNotFoundException("Parent category not found");
            }
        }
        category.setParentId(parentCategoryId);

        this.categoryRepository.save(category);
        log.info("Category {} is saved", category.getId());
    }

    @Transactional
    public void updateCategory(Integer categoryId, RequestCategory requestCategory) {
        final Optional<Category> categoryOptional = this.categoryRepository.retrieveCategoryById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found");
        }

        final Category category = categoryOptional.get();
        category.setName(requestCategory.getName());
        category.setIconPath(requestCategory.getIconPath());

        final Integer parentCategoryId = requestCategory.getParentCategoryId();
        if (parentCategoryId != null) {
            final Optional<Category> parentCategoryOptional = this.categoryRepository
                    .retrieveCategoryById(parentCategoryId);
            if (parentCategoryOptional.isEmpty()) {
                throw new ResourceNotFoundException("Parent category not found");
            }
        }
        category.setParentId(parentCategoryId);

        this.categoryRepository.save(category);
        log.info("Category {} is updated", categoryId);
    }

    @Transactional
    public void deleteCategory(Integer categoryId) {
        final Optional<Category> categoryOptional = this.categoryRepository.retrieveCategoryById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found");
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
