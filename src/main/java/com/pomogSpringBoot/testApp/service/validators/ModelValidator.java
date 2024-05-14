package com.pomogSpringBoot.testApp.service.validators;

import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import model.LabGlasswareModel;

import java.util.List;

public interface ModelValidator {
    List<CoreError> validateLabGlasswareModel(LabGlasswareModel labGlasswareModel);
}