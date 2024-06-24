package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.ComboFoodRequest;
import com.example.TastyKing.dto.request.ComboRequest;
import com.example.TastyKing.dto.request.UpdateComboRequest;
import com.example.TastyKing.dto.response.ComboFoodResponse;
import com.example.TastyKing.dto.response.ComboResponse;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.entity.Combo;
import com.example.TastyKing.entity.ComboFood;
import com.example.TastyKing.entity.ComboFoodId;
import com.example.TastyKing.entity.Food;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ComboMapper {

    @Autowired
    private FoodRepository foodRepository;

    public Combo toCombo(ComboRequest request) {
        Combo combo = new Combo();
        combo.setComboTitle(request.getComboTitle());
        combo.setOldPrice(request.getOldPrice());
        combo.setNewPrice(request.getNewPrice());
        combo.setOpenDate(request.getOpenDate());
        combo.setEndDate(request.getEndDate());
        combo.setComboDescription(request.getComboDescription());
        combo.setComboImage(request.getComboImage());
        combo.setComboImage2(request.getComboImage2());
        combo.setComboImage3(request.getComboImage3());

        combo.setComboFoods(
                request.getComboFoods().stream()
                        .map(foodRequest -> toComboFood(foodRequest, combo))
                        .collect(Collectors.toSet())
        );

        return combo;
    }
    public ComboResponse toComboResponse(Combo combo) {
        ComboResponse comboResponse = new ComboResponse();
        comboResponse.setComboID(combo.getComboID());
        comboResponse.setComboTitle(combo.getComboTitle());
        comboResponse.setOldPrice(combo.getOldPrice());
        comboResponse.setNewPrice(combo.getNewPrice());
        comboResponse.setOpenDate(combo.getOpenDate());
        comboResponse.setEndDate(combo.getEndDate());
        comboResponse.setComboDescription(combo.getComboDescription());
        comboResponse.setComboImage(combo.getComboImage());
        comboResponse.setComboImage2(combo.getComboImage2());
        comboResponse.setComboImage3(comboResponse.getComboImage3());

        comboResponse.setComboFoods(
                combo.getComboFoods().stream()
                        .map(this::toComboFoodResponse)
                        .collect(Collectors.toList())
        );

        return comboResponse;
    }

    private ComboFood toComboFood(ComboFoodRequest request, Combo combo) {
        Food food = foodRepository.findById(request.getFoodID()).orElseThrow(() -> new AppException(ErrorCode.FOOD_NOT_EXIST));
        return ComboFood.builder()
                .id(new ComboFoodId(combo.getComboID(), request.getFoodID()))
                .combo(combo)
                .food(food)
                .quantity(request.getQuantity())
                .build();
    }
    private ComboFoodResponse toComboFoodResponse(ComboFood comboFood) {
        ComboFoodResponse comboFoodResponse = new ComboFoodResponse();
        comboFoodResponse.setFoodID(comboFood.getFood().getFoodID());
        comboFoodResponse.setFoodName(comboFood.getFood().getFoodName());
        comboFoodResponse.setQuantity(comboFood.getQuantity());
        return comboFoodResponse;
    }


    public void updateComboFromRequest(Combo combo, UpdateComboRequest request) {
        combo.setComboTitle(request.getComboTitle());
        combo.setOldPrice(request.getOldPrice());
        combo.setNewPrice(request.getNewPrice());
        combo.setOpenDate(request.getOpenDate());
        combo.setEndDate(request.getEndDate());
        combo.setComboDescription(request.getComboDescription());
        combo.setComboImage(request.getComboImage());

        if (request.getComboFoods() != null) {
            combo.setComboFoods(
                    request.getComboFoods().stream()
                            .map(foodRequest -> toComboFood(foodRequest, combo))
                            .collect(Collectors.toSet())
            );
        }
    }

    public FoodResponse toFoodResponse(Food food, int quantity) {
        return FoodResponse.builder()
                .foodID(food.getFoodID())
                .category(food.getCategory())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCost(food.getFoodCost())
                .description(food.getDescription())
                .unit(food.getUnit())
                .foodImage(food.getFoodImage())
                .foodImage2(food.getFoodImage2())
                .foodImage3(food.getFoodImage3())
                .build();
    }


}