package com.example.TastyKing.controller;

import com.example.TastyKing.dto.request.TableRequest;
import com.example.TastyKing.dto.request.UpdateTableRequest;
import com.example.TastyKing.dto.request.UpdateTableStatusRequest;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.dto.response.TableResponse;
import com.example.TastyKing.service.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TableController {
    @Autowired
    private TableService tableService;

    @PostMapping
    public ApiResponse<TableResponse> createNewTable(@RequestBody @Valid TableRequest tableRequest) {
        return ApiResponse.<TableResponse>
                        builder().
                result(tableService.createNewTable(tableRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TableResponse>> getTable() {
        return ApiResponse.<List<TableResponse>>builder()
                .result(tableService.getAllTable())
                .build();
    }


    @PutMapping("/{tableID}")
    public ApiResponse<TableResponse> updateTableStatus(@PathVariable("tableID") Long tableID, @RequestBody UpdateTableStatusRequest request) {
        TableResponse response = tableService.updateTableStatus(tableID, request);
        return ApiResponse.<TableResponse>builder().result(response).build();
    }

    @DeleteMapping("/{tableID}")
    public ApiResponse<String> deleteTable(@PathVariable("tableID") Long tableID) {
        return ApiResponse.<String>builder()
                .result(tableService.deleteTable(tableID))
                .build();
    }

    @GetMapping("/{tablePositionID}")
    public ApiResponse<List<TableResponse>> getTableByPosition(@PathVariable("tablePositionID") Long tablePositionID){
        return ApiResponse.<List<TableResponse>>builder()
                .result(tableService.getTableByPosition(tablePositionID))
                .build();
    }

}

