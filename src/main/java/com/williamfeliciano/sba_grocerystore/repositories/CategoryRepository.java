package com.williamfeliciano.sba_grocerystore.repositories;

import com.williamfeliciano.sba_grocerystore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentCategoryIsNull(); // Custom query to get main categories
    List<Category> findByParentCategory(Category parentCategory); // Custom query to get subcategories
    Optional<Category> findByName(String name);
}
