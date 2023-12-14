package com.williamfeliciano.sba_grocerystore.services;

import com.williamfeliciano.sba_grocerystore.entities.Category;
import com.williamfeliciano.sba_grocerystore.exceptions.AppException;
import com.williamfeliciano.sba_grocerystore.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;


    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void givenAListOfCategories_whenGetAllCategories_thenReturnCategoriesList() {
        var listOfCategories = List.of(Category.builder().name("Electronics").build(), Category.builder().name("Clothing").build());
        BDDMockito.given(categoryRepository.findByParentCategoryIsNull()).willReturn(listOfCategories);
        // when action or behaviour
        var result = categoryService.getAllCategories();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("Electronics");
        Assertions.assertThat(result.get(1).getName()).isEqualTo("Clothing");
    }

    @Test
    public void givenAListOfSubCategories_whenGetSubcategorie_thenReturnSubCategoriesList() {
        // given precondition
        var listOfSubCategories = List.of(Category.builder().name("Laptops").build());
        BDDMockito.given(categoryRepository.findByName("Electronics")).willReturn(java.util.Optional.of(Category.builder().name("Electronics").build()));
        BDDMockito.given(categoryRepository.findByParentCategory(Category.builder().name("Electronics").build())).willReturn(listOfSubCategories);
        // when action or behaviour
        var result = categoryService.getSubcategories("Electronics");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("Laptops");
    }

    @Test
    public void givenAValidCategoryName_wheneGetCategoryByName_thenReturnCategory() {
        // given precondition
        var categoryName = "Electronics";
        var category = Category.builder().name(categoryName).parentCategory(null).build();
        BDDMockito.given(categoryRepository.findByName(categoryName)).willReturn(java.util.Optional.of(category));

        var result = categoryService.getCategoryByName(categoryName);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("Electronics");
    }

    @Test
    public void givenAnInvalidCategoryName_whenGetCategoryByName_thenThrowsException() {
       var categoryName = "Electronicss";
       BDDMockito.given(categoryRepository.findByName(categoryName)).willThrow(new AppException("Category not found", HttpStatus.NOT_FOUND));
        assertThrows(AppException.class, () -> categoryService.getCategoryByName(categoryName));
    }
}
