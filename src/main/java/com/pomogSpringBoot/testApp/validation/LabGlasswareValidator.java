package com.pomogSpringBoot.testApp.validation;

import com.pomogSpringBoot.testApp.model.LabGlasswareModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class LabGlasswareValidator implements ConstraintValidator<LabGlasswareModel, String> {
    private String prefix
    @Override
    public void initialize(LabGlasswareModel constraintAnnotation) {
       prefix = constraintAnnotation
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
