package com.williamfeliciano.sba_grocerystore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamfeliciano.sba_grocerystore.config.RestExceptionHandler;
import com.williamfeliciano.sba_grocerystore.entities.Category;
import com.williamfeliciano.sba_grocerystore.entities.Item;
import com.williamfeliciano.sba_grocerystore.services.CategoryService;
import com.williamfeliciano.sba_grocerystore.services.ItemsService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriesController.class)
@Import({AopAutoConfiguration.class, RestExceptionHandler.class})
@ActiveProfiles("test")
public class CategoriesControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ItemsService itemsService;

    @Test
    public void givenAListOfCategories_whenGetAllCategories_thenReturnsCategoriesList() throws Exception {
        var categories = List.of(Category.builder().name("Electronics").build(), Category.builder().name("Clothing").build());
        BDDMockito.given(categoryService.getAllCategories()).willReturn(categories);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(categories.size()))
                .andExpect(jsonPath("$[0]").value("Electronics"));
    }

    @Test
    public void givenAListOfSubcategories_whenGetSubcategories_thenReturnsSubcategoriesList() throws Exception {
        var category = "Electronics";
        var subcategories = List.of(Category.builder().name("Laptops").parentCategory(Category.builder().name("Electonics").build()).build());
        BDDMockito.given(categoryService.getCategoryByName(category)).willReturn(subcategories.get(0));
        BDDMockito.given(categoryService.getSubcategories(category)).willReturn(subcategories);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{category}/subcategories", category).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(subcategories.size()))
                .andExpect(jsonPath("$[0]").value("Laptops"));
    }

    @Test
    public void givenAValidCategoryName_whenGetItemsFromCategory_thenReturnsItemsOfCategory() throws Exception {
        var category = "Electronics";
        var subcategories = List.of(Category.builder().name("Laptops").parentCategory(Category.builder().id(1L).name("Electonics").build()).build());
        BDDMockito.given(categoryService.getCategoryByName(category)).willReturn(subcategories.get(0));
        var item1 = Item.builder().name("Smartphone").description("Latest model").price(BigDecimal.valueOf( 699.99)).category(Category.builder().name("Electronics").build()).pictureUrl("https://example.com/smartphone.jpg").weight(0.3).stockAmount(50).build();
        var item2 = Item.builder().name("Laptop X1").description("Powerful laptop").price(BigDecimal.valueOf(1499.99)).category(Category.builder().name("Electronics").build()).pictureUrl("https://example.com/laptopx1.jpg").weight(2.5).stockAmount(20).build();
        var items = List.of(item1, item2);
        BDDMockito.given(itemsService.getItemsByCategoryId(subcategories.get(0).getId())).willReturn(items);

       mockMvc.perform(MockMvcRequestBuilders.get("/categories/{category}/items", category).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(items.size()))
                .andExpect(jsonPath("$[0].name").value("Smartphone"))
                .andExpect(jsonPath("$[1].name").value("Laptop X1"));


    }
}
