package com.williamfeliciano.sba_grocerystore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamfeliciano.sba_grocerystore.config.RestExceptionHandler;
import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.ReducedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.exceptions.AppException;
import com.williamfeliciano.sba_grocerystore.services.ItemsService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemsController.class)
@Import({AopAutoConfiguration.class, RestExceptionHandler.class})
@ActiveProfiles("test")
public class ItemsControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemsService itemsService;

    @Test
    public void givenAValidListOfItems_whenGetAllItems_thenReturnTheListOfItmes() throws Exception {
        var reducedItemResponseDtoList = List.of(ReducedItemResponseDto.builder().id(1L).name("Item1").price("19.99").category("Category A").build(), ReducedItemResponseDto.builder().id(2L).name("Item2").price("29.99").category("Category B").build(), ReducedItemResponseDto.builder().id(3L).name("Item3").price("14.50").category("Category C").build());
        BDDMockito.given(itemsService.getAllItems()).willReturn(reducedItemResponseDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(reducedItemResponseDtoList.size()))
                .andExpect(jsonPath("$[0].name").value("Item1"));
    }

    @Test
    public void givenAValidId_whenGetItemById_thenReturnValidItem() throws Exception {
        var detailedItemResponseDto = DetailedItemResponseDto.builder().id(1L).name("Item1").price("19.99").category("Category A").pictureUrl("https://example.com/item1.jpg").description("Description for Item 1").weight(500).stockAmount(100).build();
        BDDMockito.given(itemsService.getItemById(1L)).willReturn(detailedItemResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/items/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(detailedItemResponseDto.getName()));
    }

    @Test
    public void given_when_then() throws Exception {
        var id = 999L;
        BDDMockito.given(itemsService.getItemById(id)).willThrow(new AppException("Item not found", HttpStatus.NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Item not found"));
    }
}
