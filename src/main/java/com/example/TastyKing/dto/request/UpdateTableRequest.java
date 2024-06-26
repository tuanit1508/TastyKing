package com.example.TastyKing.dto.request;

import com.example.TastyKing.entity.TablePosition;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateTableRequest {

    private TablePosition tablePosition;
    private String tableName;

    private Integer numOfchair;
}
