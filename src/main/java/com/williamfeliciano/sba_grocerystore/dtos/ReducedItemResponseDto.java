package com.williamfeliciano.sba_grocerystore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReducedItemResponseDto {

    private long id;
    private String name;
    private String price;
    private String category;
}
