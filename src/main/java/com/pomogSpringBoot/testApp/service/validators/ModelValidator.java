package com.pomogSpringBoot.testApp.service.validators;

import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import com.pomogSpringBoot.testApp.model.LabGlasswareModel;

import java.util.List;

public interface ModelValidator {
    List<CoreError> validateLabGlasswareModel(LabGlasswareModel labGlasswareModel);
}