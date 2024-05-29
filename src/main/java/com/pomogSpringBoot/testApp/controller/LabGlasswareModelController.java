package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.errorRespose.LabGlasswareException;
import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import com.pomogSpringBoot.testApp.service.dataService.ObjectTranformer;
import com.pomogSpringBoot.testApp.service.dbService.LabGlasswareService;
import com.pomogSpringBoot.testApp.service.validators.ModelValidator;
import com.pomogSpringBoot.testApp.model.GlassJointModel;
import com.pomogSpringBoot.testApp.model.LabGlasswareModel;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LabGlasswareModelController {
    @Qualifier("LabGlasswareModelValidator")
    private final ModelValidator validator;
    private final ObjectTranformer<LabGlasswareModel, LabGlassware> objectTranformer;
    private final LabGlasswareService labGlasswareService;
    private static final Logger logger = LoggerFactory.getLogger(LabGlasswareModelController.class);
    
    @Autowired
    public LabGlasswareModelController(ModelValidator validator, ObjectTranformer<LabGlasswareModel, LabGlassware> objectTranformer, LabGlasswareService labGlasswareService) {
        this.validator = validator;
        this.objectTranformer = objectTranformer;
        this.labGlasswareService = labGlasswareService;
    }
    
    @InitBinder
    public void stringTrimmer(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    
    @GetMapping("/list")
    public String listLabGlassware (Model theModel){
        theModel.addAttribute("labGlassware", labGlasswareService.findAllLabGlassware());
        
        return "lab-glassware-list";
    }
    
    
    @GetMapping("/lab-glassware-form")
    public String showForm(Model theModel) {
        theModel.addAttribute("labGlasswareModel", new LabGlasswareModel());
        theModel.addAttribute("jointTypesList", JointType.values());
        
        return "lab-glassware-form";
    }
    
    @PostMapping("/processLabGlasswareForm")
    public String processLabGlasswareSaveForm(
            @Valid
            @ModelAttribute("labGlasswareModel") LabGlasswareModel labGlasswareModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "jointTypes", required = false) List<String> jointTypes,
            @RequestParam(value = "sizeDesignations", required = false) List<String> sizeDesignations) {
        
        if (bindingResult.hasErrors()){
            return "lab-glassware-form";
        }
        
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
        
        redirectAttributes.addFlashAttribute("successMessage", "Lab Glassware saved successfully.");
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
