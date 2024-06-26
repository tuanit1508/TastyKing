package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.CategoryRequest;
import com.example.TastyKing.dto.request.TablePositionRequest;
import com.example.TastyKing.dto.response.CategoryResponse;
import com.example.TastyKing.dto.response.TablePositionResponse;
import com.example.TastyKing.entity.Category;
import com.example.TastyKing.entity.TablePosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TablePositionMapper {
    TablePosition toTablePosition(TablePositionRequest request);
    TablePositionResponse toTablePositionResponse(TablePosition tablePosition);


}

