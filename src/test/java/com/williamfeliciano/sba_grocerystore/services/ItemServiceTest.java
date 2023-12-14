package com.williamfeliciano.sba_grocerystore.services;

import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.ReducedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.Category;
import com.williamfeliciano.sba_grocerystore.entities.Item;
import com.williamfeliciano.sba_grocerystore.exceptions.AppException;
import com.williamfeliciano.sba_grocerystore.mappers.ItemMapper;
import com.williamfeliciano.sba_grocerystore.repositories.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemsService itemsService;

    @Test
    public void givenAListOfItems_whenGetAllItems_thenReturnTheListOfItems() {
        var items = List.of(Item.builder().name("Item1").build(), Item.builder().name("Item2").build(), Item.builder().name("Item3").build());
        var reducedItemResponseDtoList = List.of(ReducedItemResponseDto.builder().id(1L).name("Item1").price("19.99").category("Category A").build(), ReducedItemResponseDto.builder().id(2L).name("Item2").price("29.99").category("Category B").build(), ReducedItemResponseDto.builder().id(3L).name("Item3").price("14.50").category("Category C").build());
        BDDMockito.given(itemRepository.findAll()).willReturn(items);
        BDDMockito.given(itemMapper.toReducedItemResponseDtoList(items)).willReturn(reducedItemResponseDtoList);
        var result = itemsService.getAllItems();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("Item1");
        Assertions.assertThat(result.get(1).getName()).isEqualTo("Item2");
        Assertions.assertThat(result.get(2).getName()).isEqualTo("Item3");
    }

    @Test
    public void givenAValidItemId_whenGetItemById_thenReturnTheItem() {
        var id = 1L;
        var item = Item.builder().id(id).name("Item1").price(BigDecimal.valueOf(19.99)).category(Category.builder().name("Category A").build()).pictureUrl("https://example.com/item1.jpg").description("Description for Item 1").weight(500).stockAmount(100).build();
        var detailedItemResponseDto = DetailedItemResponseDto.builder().id(id).name("Item1").price("19.99").category("Category A").pictureUrl("https://example.com/item1.jpg").description("Description for Item 1").weight(500).stockAmount(100).build();
        BDDMockito.given(itemRepository.findById(id)).willReturn(java.util.Optional.of(item));
        BDDMockito.given(itemMapper.toDetailedItemResponseDto(item)).willReturn(detailedItemResponseDto);

        var result = itemsService.getItemById(id);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo(detailedItemResponseDto.getName());
        Assertions.assertThat(result).isEqualTo(detailedItemResponseDto);
    }

    @Test
    public void givenAnInvalidId_whenGetItemById_thenThrowsException() {
        var id = 999L;
        assertThrows(AppException.class, () -> itemsService.getItemById(id));
    }

    @Test
    public void givenAValidCategoryId_whenGetItemsByCategoryId_thenReturnListOfItems() {
        var categoryID = 1L;
        var item1 = Item.builder().name("Smartphone").description("Latest model").price(BigDecimal.valueOf( 699.99)).category(Category.builder().name("Electronics").build()).pictureUrl("https://example.com/smartphone.jpg").weight(0.3).stockAmount(50).build();
        var item2 = Item.builder().name("Laptop X1").description("Powerful laptop").price(BigDecimal.valueOf(1499.99)).category(Category.builder().name("Electronics").build()).pictureUrl("https://example.com/laptopx1.jpg").weight(2.5).stockAmount(20).build();
        var detailedItemResponseDto1 = DetailedItemResponseDto.builder().id(1L).name("Smartphone").price("699.99").category("Electronics").pictureUrl("https://example.com/smartphone.jpg").description("Latest model").weight(0.3).stockAmount(50).build();
        var detailedItemResponseDto2 = DetailedItemResponseDto.builder().id(2L).name("Laptop X1").price("1499.99").category("Electronics").pictureUrl("https://example.com/laptopx1.jpg").description("Powerful laptop").weight(2.5).stockAmount(20).build();
        var items = List.of(item1, item2);
        var dtoList = List.of(detailedItemResponseDto1, detailedItemResponseDto2);
        BDDMockito.given(itemRepository.findByCategoryId(categoryID)).willReturn(java.util.Optional.of(items));
        BDDMockito.given(itemMapper.toDetailedItemResponseDto(item1)).willReturn(detailedItemResponseDto1);
        BDDMockito.given(itemMapper.toDetailedItemResponseDto(item2)).willReturn(detailedItemResponseDto2);
        var result = itemsService.getItemsByCategoryId(categoryID);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("Smartphone");
        Assertions.assertThat(result.get(1).getName()).isEqualTo("Laptop X1");
    }
}
