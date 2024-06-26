package com.example.TastyKing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TablePositionResponse {

    private Long tablePositionID;
    private int tableQuantity;
    private String tablePosition;
}
