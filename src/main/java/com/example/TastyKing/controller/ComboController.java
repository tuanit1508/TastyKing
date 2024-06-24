package com.example.TastyKing.controller;

import com.example.TastyKing.dto.request.ComboRequest;
import com.example.TastyKing.dto.request.UpdateComboRequest;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.dto.response.ComboResponse;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.entity.Combo;
import com.example.TastyKing.service.ComboService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/combo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ComboController {
    @Autowired
    private ComboService comboService;

    @PostMapping
    public ApiResponse<ComboResponse> createNewCombo(@RequestBody @Valid ComboRequest request) {
        return ApiResponse.<ComboResponse>builder()
                .result(comboService.createNewCombo(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ComboResponse>> getAllCombo(){
        return ApiResponse.<List<ComboResponse>>builder()
                .result(comboService.getAllCombo())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{comboID}")
    public ApiResponse<String> deleteCombo(@PathVariable("comboID") Long comboID){
        return ApiResponse.<String>builder()
                .result(comboService.deleteCombo(comboID))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{comboID}")
    public ApiResponse<ComboResponse> updateCombo(@PathVariable("comboID") Long comboID, @RequestBody UpdateComboRequest updateComboRequest){
        return ApiResponse.<ComboResponse>builder()
                .result(comboService.updateCombo(comboID, updateComboRequest))
                .build();
    }

    @GetMapping("getCombo/{comboID}")
    public ApiResponse<ComboResponse> getComboByComboID(@PathVariable("comboID") Long comboID){
        return ApiResponse.<ComboResponse>builder()
                .result(comboService.getComboByID(comboID))
                .build();
    }
    @GetMapping("getComboFood/{comboID}")
    public ApiResponse<List<FoodResponse>> getFoodByComboID(@PathVariable("comboID") Long comboID){
        return ApiResponse.<List<FoodResponse>>builder()
                .result(comboService.getFoodByComboID(comboID))
                .build();

    }
}
