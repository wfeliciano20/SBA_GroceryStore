package com.williamfeliciano.sba_grocerystore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailedItemResponseDto {

    private long id;
    private String name;
    private String description;
    private String price;
    private String category;
    private String pictureUrl;
    private double weight;
    private int stockAmount;
}
