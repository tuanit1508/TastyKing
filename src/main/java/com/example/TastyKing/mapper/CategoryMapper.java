package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.CategoryRequest;
import com.example.TastyKing.dto.response.CategoryResponse;
import com.example.TastyKing.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
}
