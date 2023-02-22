package com.lhstore.productcatalogservice.controller;

import com.lhstore.productcatalogservice.dto.RequestCategory;
import com.lhstore.productcatalogservice.dto.ResponseCategory;
import com.lhstore.productcatalogservice.service.CategoryService;
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
    public void createCategory(@Valid @RequestBody RequestCategory requestCategory) {
        this.categoryService.createCategory(requestCategory);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCategory(@PathVariable("id") Integer categoryId,
                               @Valid @RequestBody RequestCategory requestCategory) {
        this.categoryService.updateCategory(categoryId, requestCategory);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public Set<ResponseCategory> retrieveAllCategories() {
        return this.categoryService.retrieveCategories();
    }
}
