package com.pomogSpringBoot.testApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LabGlasswareValidator implements ConstraintValidator<FieldValidation, String> {
    private String prefix;
    @Override
    public void initialize(FieldValidation constraintAnnotation) {
        prefix = constraintAnnotation.value();
    }
    
    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        if (field == null) {
            return false;
        }
        return field.startsWith(prefix);
    }
}
