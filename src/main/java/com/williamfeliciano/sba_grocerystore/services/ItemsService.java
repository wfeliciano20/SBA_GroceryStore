package com.williamfeliciano.sba_grocerystore.services;

import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.ReducedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.Item;
import com.williamfeliciano.sba_grocerystore.exceptions.AppException;
import com.williamfeliciano.sba_grocerystore.mappers.ItemMapper;
import com.williamfeliciano.sba_grocerystore.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ReducedItemResponseDto> getAllItems() {
        var dbItems = itemRepository.findAll();
        return itemMapper.toReducedItemResponseDtoList(dbItems);
    }

    public DetailedItemResponseDto getItemById(long id) {
        var dbItem = itemRepository.findById(id).orElseThrow(() -> new AppException("Item not found", HttpStatus.NOT_FOUND));
        return itemMapper.toDetailedItemResponseDto(dbItem);
    }

    public List<Item> getItemsByCategoryId(long categoryId) {
        return itemRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new AppException(String.format("No Items found for category with id %s", categoryId), HttpStatus.NOT_FOUND));
    }

}
