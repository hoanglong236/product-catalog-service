package com.lhstore.productcatalogservice.category;

import com.lhstore.productcatalogservice.exceptions.ResourceAlreadyExistsException;
import com.lhstore.productcatalogservice.exceptions.ResourceNotFoundException;
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
        if (categoryRepository.isCategoryNameExists(requestCategory.getName())) {
            throw new ResourceAlreadyExistsException("Category name already exists");
        }

        final Category category = new Category();
        category.setName(requestCategory.getName());
        category.setIconPath(requestCategory.getIconPath());

        final Integer parentCategoryId = requestCategory.getParentCategoryId();
        if (parentCategoryId != null && !categoryRepository.isCategoryIdExists(parentCategoryId)) {
            throw new ResourceNotFoundException("Could not find the parent category");
        }
        category.setParentId(parentCategoryId);

        categoryRepository.save(category);
        log.info("Category {} is saved", category.getId());
    }

    @Transactional
    public void updateCategory(int categoryId, RequestCategory requestCategory) {
        final Optional<Category> categoryOptionalById = this.categoryRepository.retrieveById(categoryId);
        if (categoryOptionalById.isEmpty()) {
            throw new ResourceNotFoundException("Could not find the category");
        }

        final Category category = categoryOptionalById.get();
        final String requestCategoryName = requestCategory.getName();
        if (!category.getName().equals(requestCategoryName) &&
                categoryRepository.isCategoryNameExists(requestCategoryName)) {

            throw new ResourceAlreadyExistsException("Category name already exists");
        }
        category.setName(requestCategoryName);
        category.setIconPath(requestCategory.getIconPath());

        final Integer parentCategoryId = requestCategory.getParentCategoryId();
        if (parentCategoryId != null && !categoryRepository.isCategoryIdExists(parentCategoryId)) {
            throw new ResourceNotFoundException("Could not find the parent category");
        }
        category.setParentId(parentCategoryId);

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
    public Set<ResponseCategory> retrieveCategories() {
        final Set<Category> categories = categoryRepository.retrieveCategories();
        return categoryMapper.mapToResponseCategories(categories);
    }
}
