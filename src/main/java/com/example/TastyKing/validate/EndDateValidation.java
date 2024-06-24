package com.example.TastyKing.validate;

import com.example.TastyKing.dto.request.ComboRequest;
import com.example.TastyKing.entity.Combo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.util.Objects;

public class EndDateValidation implements ConstraintValidator<EndDateConstraint, Object> {

    @Override
    public void initialize(EndDateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj == null) {
            return true; // or false, depending on your needs
        }
        try {
            ComboRequest combo = (ComboRequest) obj;
            if (combo.getOpenDate() == null || combo.getEndDate() == null) {
                return true; // or false, depending on your needs
            }
            return combo.getEndDate().isAfter(combo.getOpenDate());
        } catch (ClassCastException e) {
            return false;
        }
    }
}
