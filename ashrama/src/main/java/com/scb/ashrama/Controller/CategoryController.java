package com.scb.ashrama.Controller;

import com.scb.ashrama.Domain.Category;
import com.scb.ashrama.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        log.info("Received request to create category: {}", category);
        Category createdCategory = categoryService.createCategory(category);
        log.info("Category created successfully with ID: {}", createdCategory.getId());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        log.info("Received request to fetch all categories");
        List<Category> categories = categoryService.getAllCategories();
        log.info("Fetched {} categories", categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        log.info("Received request to fetch category with ID: {}", id);
        Category category = categoryService.getCategoryById(id);
        log.info("Category with ID: {} fetched successfully", id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Update a category by ID
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        log.info("Received request to update category with ID: {}", id);
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        log.info("Category with ID: {} updated successfully", id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.info("Received request to delete category with ID: {}", id);
        categoryService.deleteCategory(id);
        log.info("Category with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
