package com.example.TastyKing.dto.response;

import com.example.TastyKing.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FoodResponse {
    private Long foodID;
    private Category category;


    private String foodName;


    private Double foodPrice;


    private Double foodCost;


    private String description;

    private String unit;


    private String foodImage;

}
