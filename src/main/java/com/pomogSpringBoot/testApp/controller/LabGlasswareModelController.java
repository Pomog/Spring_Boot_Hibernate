package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.errorRespose.LabGlasswareException;
import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import com.pomogSpringBoot.testApp.service.dataService.ObjectTranformer;
import com.pomogSpringBoot.testApp.service.dbService.LabGlasswareService;
import com.pomogSpringBoot.testApp.service.validators.ModelValidator;
import model.GlassJointModel;
import model.LabGlasswareModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class LabGlasswareModelController {
    private final ModelValidator validator;
    private final ObjectTranformer<LabGlasswareModel, LabGlassware> objectTranformer;
    
    private final LabGlasswareService labGlasswareService;
    
    @Autowired
    public LabGlasswareModelController(ModelValidator validator, ObjectTranformer<LabGlasswareModel, LabGlassware> objectTranformer, LabGlasswareService labGlasswareService) {
        this.validator = validator;
        this.objectTranformer = objectTranformer;
        this.labGlasswareService = labGlasswareService;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(LabGlasswareModelController.class);
    
    @GetMapping("/lab-glassware-form")
    public String showForm(Model theModel) {
        LabGlasswareModel labGlasswareModel = new LabGlasswareModel();

        theModel.addAttribute("labGlasswareModel", labGlasswareModel);
        theModel.addAttribute("jointTypesList", JointType.values());
        
        return "lab-glassware-form";
    }
    
    @PostMapping("/processLabGlasswareForm")
    public String processLabGlasswareSaveForm(@ModelAttribute("labGlasswareModel") LabGlasswareModel labGlasswareModel,
                                              @RequestParam("jointTypes") List<String> jointTypes,
                                              @RequestParam("sizeDesignations") List<String> sizeDesignations) {
        
        extractModel(labGlasswareModel, jointTypes, sizeDesignations);
        checkForErrors(labGlasswareModel);
        
        LabGlassware labGlassware = objectTranformer.transform(labGlasswareModel);
        labGlasswareService.save(labGlassware);

        return "lab-glassware-confirmation";
    }
    
    private void checkForErrors(LabGlasswareModel labGlasswareModel) {
        List<CoreError> errors = validator.validateLabGlasswareModel(labGlasswareModel);
        if(!errors.isEmpty()){
            String message = errors.toString();
            throw new LabGlasswareException(message);
        }
    }
    
    private static void extractModel(LabGlasswareModel labGlasswareModel, List<String> jointTypes, List<String> sizeDesignations) {
        // Create and add GlassJointModel instances
        for (int i = 0; i < jointTypes.size(); i++) {
            GlassJointModel glassJointModel = new GlassJointModel();
            glassJointModel.setType(JointType.valueOf(jointTypes.get(i))); // Assuming JointType is an enum
            glassJointModel.setSizeDesignation(sizeDesignations.get(i));
            labGlasswareModel.addGlassJoint(glassJointModel);
        }
    }
}
