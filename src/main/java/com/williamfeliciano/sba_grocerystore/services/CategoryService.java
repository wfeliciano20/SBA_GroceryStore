package com.williamfeliciano.sba_grocerystore.services;

import com.williamfeliciano.sba_grocerystore.entities.Category;
import com.williamfeliciano.sba_grocerystore.exceptions.AppException;
import com.williamfeliciano.sba_grocerystore.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findByParentCategoryIsNull();
    }

    public List<Category> getSubcategories(String categoryName) {
        Category category = getCategoryByName(categoryName);
        return categoryRepository.findByParentCategory(category);
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new AppException("Category not found", HttpStatus.NOT_FOUND));
    }
}
