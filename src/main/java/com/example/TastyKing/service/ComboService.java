package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.ComboRequest;
import com.example.TastyKing.dto.request.UpdateComboRequest;
import com.example.TastyKing.dto.response.ComboFoodResponse;
import com.example.TastyKing.dto.response.ComboResponse;
import com.example.TastyKing.dto.response.FoodResponse;
import com.example.TastyKing.entity.Combo;
import com.example.TastyKing.entity.Food;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.ComboMapper;
import com.example.TastyKing.repository.ComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComboService {
    @Autowired
    private final ComboRepository comboRepository;
    @Autowired
    private final ComboMapper comboMapper;


    @PreAuthorize("hasRole('ADMIN')")
    public ComboResponse createNewCombo(ComboRequest request) {
        Combo combo = comboMapper.toCombo(request);
        Combo savedCombo = comboRepository.save(combo);
        return comboMapper.toComboResponse(savedCombo);
    }

    public String deleteCombo(Long comboID) {
        comboRepository.deleteById(comboID);
        return "Combo deleted successfully";
    }

    public List<ComboResponse> getAllCombo() {
        List<Combo> combos = comboRepository.findAll();
        return combos.stream()
                .map(comboMapper::toComboResponse)
                .collect(Collectors.toList());
    }



    @Scheduled(cron = "0 0 * * * ?") // Chạy mỗi phút
    public void deleteExpiredCombos() {
        LocalDateTime now = LocalDateTime.now();
        List<Combo> expiredCombos = comboRepository.findByEndDateBefore(now);
        comboRepository.deleteAll(expiredCombos);
    }

    public ComboResponse updateCombo(Long comboID, UpdateComboRequest request) {
        Combo existingCombo = comboRepository.findById(comboID)
                .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_EXIST));

        comboMapper.updateComboFromRequest(existingCombo, request);
        Combo updatedCombo = comboRepository.save(existingCombo);
        return comboMapper.toComboResponse(updatedCombo);
    }

    public ComboResponse getComboByID(Long comboID){
        Combo existingCombo = comboRepository.findById(comboID)
                .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_EXIST));
        return comboMapper.toComboResponse(existingCombo);
    }

    public List<ComboFoodResponse> getFoodInCombo(Long comboID) {
        return comboMapper.getFoodFromCombo(comboID);
    }


}
