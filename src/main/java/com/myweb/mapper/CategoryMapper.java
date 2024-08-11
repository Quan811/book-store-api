package com.myweb.mapper;

import com.myweb.dto.CategoryDTO;
import com.myweb.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDTO(Category category);

    void updateCategory (CategoryDTO categoryDTO, @MappingTarget  Category category);
}
