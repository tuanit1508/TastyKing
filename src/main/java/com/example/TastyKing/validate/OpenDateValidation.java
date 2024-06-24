package com.example.TastyKing.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class OpenDateValidation implements ConstraintValidator<OpenDateConstraint, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime openDate, ConstraintValidatorContext constraintValidatorContext) {
            if (openDate == null){
                return true;
            }
            return openDate.isAfter(LocalDateTime.now());
    }

    @Override
    public void initialize(OpenDateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

    }
}
