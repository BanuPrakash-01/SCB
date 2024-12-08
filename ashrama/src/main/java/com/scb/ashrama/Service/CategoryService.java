package com.scb.ashrama.Service;

import com.scb.ashrama.Domain.Category;
import java.util.List;

public interface CategoryService {
    // Create a new category
    Category createCategory(Category category);

    // Get a category by ID
    Category getCategoryById(Long id);

    // Get all categories
    List<Category> getAllCategories();

    // Update an existing category
    Category updateCategory(Long id, Category categoryDetails);

    // Delete a category by ID
    void deleteCategory(Long id);
}
