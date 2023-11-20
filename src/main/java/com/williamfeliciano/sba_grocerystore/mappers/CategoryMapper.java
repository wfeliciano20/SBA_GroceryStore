package com.williamfeliciano.sba_grocerystore.mappers;

import com.williamfeliciano.sba_grocerystore.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    String toCategoryName(Category category);

}
