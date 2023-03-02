package com.lhstore.productcatalogservice.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCategory(@PathVariable("id") int categoryId,
            @Valid @RequestBody CategoryRequest categoryRequest) {

        categoryService.updateCategory(categoryId, categoryRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") int categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<CategoryResponse> retrieveAllCategories() {
        return categoryService.retrieveCategories();
    }
}
