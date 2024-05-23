package com.pomogSpringBoot.testApp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LabGlasswareValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValidation {
    public String value() default "lab";
    public String message() default "must start from lab";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}

