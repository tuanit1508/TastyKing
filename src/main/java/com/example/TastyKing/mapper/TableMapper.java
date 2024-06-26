package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.FoodRequest;
import com.example.TastyKing.dto.request.TableRequest;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.dto.response.TableResponse;
import com.example.TastyKing.entity.Food;
import com.example.TastyKing.entity.Tables;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    Tables toTable(TableRequest request);
    TableResponse toTableResponse(Tables tables);
}
