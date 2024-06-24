package com.example.TastyKing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ComboFoodResponse {
    private Long foodID;
    private String foodName;
    private Integer quantity;
}
