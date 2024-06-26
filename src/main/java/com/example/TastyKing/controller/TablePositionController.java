package com.example.TastyKing.controller;


import com.example.TastyKing.dto.request.TablePositionRequest;
import com.example.TastyKing.dto.request.UpdateTablePosition;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.dto.response.TablePositionResponse;
import com.example.TastyKing.dto.response.TableResponse;
import com.example.TastyKing.entity.TablePosition;
import com.example.TastyKing.service.TablePositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table-position")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TablePositionController {
    @Autowired
    private TablePositionService tablePositionService;

    @PostMapping
    public ApiResponse<TablePositionResponse> createNewPostiton(@RequestBody TablePositionRequest request){
        return ApiResponse.<TablePositionResponse>builder()
                .result(tablePositionService.createTablePosition(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TablePositionResponse>> getAllPosition(){
        return ApiResponse.<List<TablePositionResponse>>builder()
                .result(tablePositionService.getAllPosition())
                .build();
    }

    @PutMapping("/{tablePositionID}")
    public ApiResponse<TablePositionResponse> updatePositon(@PathVariable("tablePositionID") Long tablePositionID, @RequestBody UpdateTablePosition request){
        return ApiResponse.<TablePositionResponse>builder()
                .result(tablePositionService.updatePosition(tablePositionID, request))
                .build();
    }

    @DeleteMapping("/{tablePositionID}")
    public ApiResponse<String> deletePosition(@PathVariable("tablePositionID") Long tablePositionID){
        return ApiResponse.<String>builder()
                .result(tablePositionService.deletePosition(tablePositionID))
                .build();
    }
}
