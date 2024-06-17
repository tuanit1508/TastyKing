package com.example.TastyKing.dto.request;

import com.example.TastyKing.entity.Category;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FoodRequest {

    private Category category;


    private String foodName;

    @DecimalMin(value = "0.0", inclusive = false, message = "PRICE_INVALID")
    private Double foodPrice;

    @DecimalMin(value = "0.0", inclusive = false, message = "PRICE_INVALID")
    private Double foodCost;


    private String description;

    private String unit;


    private String foodImage;
}

