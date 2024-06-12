package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.FoodRequest;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food toFood(FoodRequest request);
    FoodResponse toFoodResponse(Food food);
}
