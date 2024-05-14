package com.pomogSpringBoot.testApp.service.validators;

import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import model.LabGlasswareModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LabGlasswareModelValidator implements ModelValidator {
    
    @Override
    public List<CoreError> validateLabGlasswareModel(LabGlasswareModel labGlasswareModel) {
        List<CoreError> errors = new ArrayList<>();
        
        nameValidator(errors, labGlasswareModel);
        capacityValidator(errors, labGlasswareModel);
        
        return errors;
    }
    
    private void nameValidator(List<CoreError> errors, LabGlasswareModel labGlasswareModel) {
        if (labGlasswareModel.getName() == null || labGlasswareModel.getName().isEmpty()) {
            errors.add(new CoreError("name", "Name cannot be empty"));
        }
    }
    
    private void capacityValidator(List<CoreError> errors, LabGlasswareModel labGlasswareModel) {
        if (labGlasswareModel.getCapacityML() == null || labGlasswareModel.getName().isEmpty()) {
            errors.add(new CoreError("Capacity", "Capacity cannot be empty"));
        }
    }
}
