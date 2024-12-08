package com.scb.ashrama.Service.impl;

import com.scb.ashrama.Domain.Category;
import com.scb.ashrama.Exception.ResourceNotFoundException;
import com.scb.ashrama.Repository.CategoryRepository;
import com.scb.ashrama.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    public static final String CATEGORY_NOT_FOUND_WITH_ID = "Category not found with ID: {}";
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        log.info("Creating a new category: {}", category);
        Category createdCategory = categoryRepository.save(category);
        log.info("Category created with ID: {}", createdCategory.getId());
        return createdCategory;
    }

    @Override
    public Category getCategoryById(Long id) {
        log.info("Fetching category with ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Category not found with ID: {}", id);
                    return new ResourceNotFoundException("Category not found with id " + id);
                });
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Retrieved {} categories", categories.size());
        return categories;
    }

    @Override
    public Category updateCategory(Long id, Category categoryDetails) {
        log.info("Updating category with ID: {}", id);
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_WITH_ID, id);
                    return new ResourceNotFoundException("Category not found with id " + id);
                });

        // Update fields
        existingCategory.setName(categoryDetails.getName());
        existingCategory.setDescription(categoryDetails.getDescription());
        existingCategory.setUpdatedBy(categoryDetails.getUpdatedBy());
        existingCategory.setUpdatedDate(LocalDateTime.now());

        Category updatedCategory = categoryRepository.save(existingCategory);
        log.info("Category with ID: {} updated successfully", id);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category with ID: {}", id);
        Category category = getCategoryById(id); // This will log and throw an exception if not found
        categoryRepository.delete(category);
        log.info("Category with ID: {} deleted successfully", id);
    }
}
