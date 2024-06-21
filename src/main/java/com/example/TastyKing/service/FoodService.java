package com.example.TastyKing.service;


import com.example.TastyKing.dto.request.FoodRequest;
import com.example.TastyKing.dto.request.UpdateFoodRequest;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.entity.Category;
import com.example.TastyKing.entity.Food;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.FoodMapper;
import com.example.TastyKing.repository.CategoryRepository;
import com.example.TastyKing.repository.FoodRepository;
import com.example.TastyKing.request.FoodUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
        @Autowired
        private final FoodRepository foodRepository;
        @Autowired
        private final FoodMapper foodMapper;
        @Autowired
        private final CategoryRepository categoryRepository;



    @PreAuthorize("hasRole('ADMIN')")
        public FoodResponse addFood(FoodRequest foodRequest) {
            Food food = foodMapper.toFood(foodRequest);
            Category category = categoryRepository.findById(foodRequest.getCategory().getCategoryID())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
            food.setCategory(category);
            Food savedFood = foodRepository.save(food);
            return foodMapper.toFoodResponse(savedFood);
        }

        public List<FoodResponse> getAllFood() {
            List<Food> foods = foodRepository.findAll();
            return foods.stream()
                    .map(foodMapper::toFoodResponse)
                    .collect(Collectors.toList());
        }

        public List<FoodResponse> getFoodByCategory(Long categoryId) {
            List<Food> foods = foodRepository.findByCategory_CategoryID(categoryId);
            return foods.stream()
                    .map(foodMapper::toFoodResponse)
                    .collect(Collectors.toList());
        }
        public String deleteFood(Long foodID){
            foodRepository.deleteById(foodID);
            return "Deleted successfull";
        }
        public FoodResponse updateFood(Long foodID, UpdateFoodRequest request) {
            Food food = foodRepository.findById(foodID).orElseThrow(() ->
                    new AppException(ErrorCode.FOOD_NOT_EXIST));
            Category category = categoryRepository.findById(request.getCategory().getCategoryID())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
            food.setCategory(category);
            food.setFoodName(request.getFoodName());
            food.setFoodCost(request.getFoodCost());
            food.setFoodImage(request.getFoodImage());
            food.setDescription(request.getDescription());
            food.setFoodPrice(request.getFoodPrice());
            food.setUnit(request.getUnit());
           Food updateFood= foodRepository.save(food);
           return foodMapper.toFoodResponse(updateFood);
        }
        public FoodResponse getFoodByFoodID(Long foodID){
           Food food = foodRepository.findById(foodID).orElseThrow(() ->
                   new AppException(ErrorCode.FOOD_NOT_EXIST));
           return foodMapper.toFoodResponse(food);
        }
    }