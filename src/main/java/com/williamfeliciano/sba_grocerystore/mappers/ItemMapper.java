package com.williamfeliciano.sba_grocerystore.mappers;

import com.williamfeliciano.sba_grocerystore.dtos.DetailedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.dtos.ReducedItemResponseDto;
import com.williamfeliciano.sba_grocerystore.entities.Category;
import com.williamfeliciano.sba_grocerystore.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface ItemMapper {
    @Mapping(target = "categoryName", source = "category")
    List<ReducedItemResponseDto> toReducedItemResponseDtoList(List<Item> items);
    default String CategoryNameConversion(Category category) {
        // Custom conversion logic
        // You can implement your specific conversion here
        return category.getName();
    }

    DetailedItemResponseDto toDetailedItemResponseDto(Item item);
}
