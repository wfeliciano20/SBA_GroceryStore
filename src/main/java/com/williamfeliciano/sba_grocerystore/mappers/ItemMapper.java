package com.williamfeliciano.sba_grocerystore.mappers;

import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.ReducedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryMapper.class}
)

public interface ItemMapper {

    List<ReducedItemResponseDto> toReducedItemResponseDtoList(List<Item> items);

    DetailedItemResponseDto toDetailedItemResponseDto(Item item);
}
