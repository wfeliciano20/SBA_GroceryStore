package com.williamfeliciano.sba_grocerystore.controllers;

import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.Category;
import com.williamfeliciano.sba_grocerystore.entities.Item;
import com.williamfeliciano.sba_grocerystore.services.CategoryService;
import com.williamfeliciano.sba_grocerystore.services.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;
    private final ItemsService itemsService;

    @GetMapping
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{category}/subcategories")
    public ResponseEntity<List<String>> getSubcategories(@PathVariable String category) {
        return ResponseEntity.ok(categoryService
                .getSubcategories(category)
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList()));
    }

    // Add a method for GET /categories/{category}/items if needed
    @GetMapping("/{category}/items")
    public ResponseEntity<List<DetailedItemResponseDto>> getItemsFromCategory(@PathVariable String category) {
        var dbCategory = categoryService.getCategoryByName(category);
        return ResponseEntity.ok(itemsService.getItemsByCategoryId(dbCategory.getId()));
    }
}
