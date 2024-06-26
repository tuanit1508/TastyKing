package com.example.TastyKing.dto.response;

import com.example.TastyKing.entity.TablePosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TableResponse {
    private Long tableID;
    private TablePosition tablePosition;
    private String tableName;
    private Integer numOfchair;
    private String tableStatus;
}
