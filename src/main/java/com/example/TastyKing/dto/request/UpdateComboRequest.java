package com.example.TastyKing.dto.request;

import com.example.TastyKing.validate.EndDateConstraint;
import com.example.TastyKing.validate.OpenDateConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateComboRequest {
    private String comboTitle;
    private Double oldPrice;
    private Double newPrice;

    @OpenDateConstraint(message = "OPEN_DATE_INVALID")
    private LocalDateTime openDate;

   @NotNull
    private LocalDateTime endDate;
    private String comboDescription;
    private String comboImage;
    private String comboImage2;
    private String comboImage3;
    private List<ComboFoodRequest> comboFoods;
}
