package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.glassware.JointType;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import com.pomogSpringBoot.testApp.errorRespose.LabGlasswareException;
import com.pomogSpringBoot.testApp.errorRespose.error.CoreError;
import com.pomogSpringBoot.testApp.service.dataService.ObjectTranformer;
import com.pomogSpringBoot.testApp.service.dbService.LabGlasswareFilter;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LabGlasswareModelController {
    private static final Logger logger = LoggerFactory.getLogger(LabGlasswareModelController.class);
    @Qualifier("LabGlasswareModelValidator")
    private final ModelValidator validator;
    private final ObjectTranformer<LabGlasswareModel, LabGlassware> objectTranformer;
    private final LabGlasswareService labGlasswareService;
    
    @Autowired
    public LabGlasswareModelController(
            ModelValidator validator,
            ObjectTranformer<LabGlasswareModel,
                    LabGlassware> objectTranformer,
            LabGlasswareService labGlasswareService) {
        this.validator = validator;
        this.objectTranformer = objectTranformer;
        this.labGlasswareService = labGlasswareService;
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
    
    @InitBinder
    public void stringTrimmer(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    
    @GetMapping("/list")
    public String listLabGlasswareByCapacity(
            Model theModel,
            @RequestParam(value = "maxVol", required = false) Integer max,
            @RequestParam(value = "minVol", required = false) Integer min,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "broken", required = false) Boolean broken,
            @RequestParam(value = "repaired", required = false) Boolean repaired
    
    ) {
        LabGlasswareFilter filter = new LabGlasswareFilter();
        filter.setMaxVol(max);
        filter.setMinVol(min);
        filter.setName(name);
        filter.setBroken(broken);
        filter.setRepaired(repaired);
        
        List<LabGlasswareDTO> labGlasswareList;
        
        /*
        Here interface Specification and JpaRepository used
        */
        if ((min != null && max != null && min > 0 && max > 0 && min <= max) || name != null || broken != null || repaired != null) {
            labGlasswareList = labGlasswareService.findLabGlassware(filter);
        } else {
            labGlasswareList = labGlasswareService.findAllLabGlassware();
        }
        
        theModel.addAttribute("labGlassware", labGlasswareList);
        return "lab-glassware-list";
    }
    
    @GetMapping("/lab-glassware-form")
    public String showForm(
            Model theModel,
            @RequestParam(value = "id", required = false) Long id) throws IllegalAccessException {
        if (id != null && id != 0) {
            LabGlassware labGlassware = labGlasswareService.findLabGlasswareByID(id);
            var labGlasswareModel = new LabGlasswareModel(labGlassware);
            theModel.addAttribute("labGlasswareModel", labGlasswareModel);
        } else {
            theModel.addAttribute("labGlasswareModel", new LabGlasswareModel());
        }
        theModel.addAttribute("jointTypesList", JointType.values());
        
        return "lab-glassware-form";
    }
    
    @PostMapping("/processLabGlassware")
    public String processLabGlasswareSaveForm(
            @Valid
            @ModelAttribute("labGlasswareModel") LabGlasswareModel labGlasswareModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "jointTypes", required = false) List<String> jointTypes,
            @RequestParam(value = "sizeDesignations", required = false) List<String> sizeDesignations,
            @RequestParam("action") String action,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IllegalAccessException {
        
        if (bindingResult.hasErrors() && !action.equals("delete")) {
            return "lab-glassware-form";
        }
        
        if (jointTypes == null) {
            jointTypes = new ArrayList<>();
        }
        if (sizeDesignations == null) {
            sizeDesignations = new ArrayList<>();
        }
        
        switch (action) {
            case "update":
                System.out.println("CASE update");
                extractModel(labGlasswareModel, jointTypes, sizeDesignations);
                checkForErrors(labGlasswareModel);
                
                System.out.println("labGlasswareModel");
                System.out.println(labGlasswareModel);
                
                LabGlassware labGlassware = objectTranformer.transform(labGlasswareModel);
                
                System.out.println("labGlassware");
                System.out.println(labGlassware);
                
                if (imageFile.isEmpty()) {
                    // is object present in the DB and contains image?
                    LabGlassware oldObject = labGlasswareService.findLabGlasswareByID(labGlassware.getId());
                    if (oldObject != null) {
                        labGlassware.setImage(oldObject.getImage());
                    }
                    
                    labGlasswareService.save(labGlassware);
                } else {
                    labGlasswareService.saveWithImage(labGlassware, imageFile);
                }
                
                redirectAttributes.addFlashAttribute("successMessage", "Lab Glassware updated successfully.");
                break;
            
            case "delete":
/*
TODO: This is CODE error: processLabGlasswareSaveForm works with POST but runs DELETE in the DB
In this way authorities restriction -> only ADMIN can DELETE is bypassed
 */
                labGlasswareService.deleteByID(labGlasswareModel.getId());
                
                redirectAttributes.addFlashAttribute("successMessage", "Lab Glassware deleted successfully.");
                break;
            
            default:
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid action.");
                break;
        }
        
        return "redirect:/list";
    }
    
    private void checkForErrors(LabGlasswareModel labGlasswareModel) {
        List<CoreError> errors = validator.validateLabGlasswareModel(labGlasswareModel);
        if (!errors.isEmpty()) {
            throw new LabGlasswareException(errors.toString());
        }
    }
    
}
