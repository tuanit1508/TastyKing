package com.example.TastyKing.controller;


import com.example.TastyKing.dto.request.FoodRequest;
import com.example.TastyKing.dto.request.UpdateFoodRequest;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ApiResponse<FoodResponse> addNewFood(@RequestBody FoodRequest foodRequest) {
        return ApiResponse.<FoodResponse>builder()
                .result(foodService.addFood(foodRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<FoodResponse>> getAllFood() {
        return ApiResponse.<List<FoodResponse>>builder()
                .result(foodService.getAllFood()).build();
    }

    @GetMapping("/{categoryID}")
    public ApiResponse<List<FoodResponse>> getAllFoodByCid(@PathVariable("categoryID") Long categoryID) {
        return ApiResponse.<List<FoodResponse>>builder()
                .result(foodService.getFoodByCategory(categoryID)).build();
    }

    @DeleteMapping("/{foodID}")
    public ApiResponse<String> deleteFood(@PathVariable("foodID") Long foodID) {

        return ApiResponse.<String>builder()
                .result(foodService.deleteFood(foodID))
                .build();
    }

    @PutMapping("/{foodID}")
    public ApiResponse<FoodResponse> updateFood(@PathVariable("foodID") Long foodID, @RequestBody UpdateFoodRequest request) {
        return ApiResponse.<FoodResponse>builder()
                .result(foodService.updateFood(foodID, request))
                .build();
    }

    @GetMapping("/getFood/{foodID}")
    public ApiResponse<FoodResponse> getFoodByID(@PathVariable("foodID") Long foodID) {
            return ApiResponse.<FoodResponse>builder()
                    .result(foodService.getFoodByFoodID(foodID))
                    .build();
    }
}
