package com.example.TastyKing.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)


@Constraint(
        validatedBy = {OpenDateValidation.class}
)
public @interface OpenDateConstraint {
    String message() default "Invalid open date";



    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
