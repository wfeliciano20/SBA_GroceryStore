package com.williamfeliciano.sba_grocerystore.controllers;

import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.ReducedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.services.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemsController {

    private final ItemsService itemsService;

    @GetMapping
    public ResponseEntity<List<ReducedItemResponseDto>> getAllItems(){
        return ResponseEntity.ok(itemsService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedItemResponseDto> getItemById(@PathVariable long id){
        return ResponseEntity.ok(itemsService.getItemById(id));
    }
}
