package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.errorRespose.LabGlasswareException;
import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import com.pomogSpringBoot.testApp.service.dataService.ObjectTranformer;
import com.pomogSpringBoot.testApp.service.dbService.LabGlasswareService;
import com.pomogSpringBoot.testApp.service.validators.ModelValidator;
import com.pomogSpringBoot.testApp.model.GlassJointModel;
import com.pomogSpringBoot.testApp.model.LabGlasswareModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LabGlasswareModelController {
    @Qualifier("LabGlasswareModelValidator")
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
                                              @RequestParam(value = "jointTypes", required = false) List<String> jointTypes,
                                              @RequestParam(value = "sizeDesignations", required = false) List<String> sizeDesignations) {

        if (jointTypes == null) {
            jointTypes = new ArrayList<>();
        }
        if (sizeDesignations == null) {
            sizeDesignations = new ArrayList<>();
        }
        
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
        if (jointTypes.size() != sizeDesignations.size()) {
            throw new IllegalArgumentException("The size of jointTypes and sizeDesignations must be the same");
        }
        
        for (int i = 0; i < jointTypes.size(); i++) {
            GlassJointModel glassJointModel = new GlassJointModel();

            String jointType = jointTypes.get(i);
            if (jointType != null && !jointType.isEmpty()) {
                try {
                    glassJointModel.setType(JointType.valueOf(jointType));
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid JointType: " + jointType, e);
                }
            }
            
            String sizeDesignation = sizeDesignations.get(i);
            if (sizeDesignation != null && !sizeDesignation.isEmpty()) {
                glassJointModel.setSizeDesignation(sizeDesignation);
            }
            
            labGlasswareModel.addGlassJoint(glassJointModel);
        }
    }

}
