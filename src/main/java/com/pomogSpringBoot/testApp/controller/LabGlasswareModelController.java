package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.entity.JointType;
import model.LabGlasswareModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LabGlasswareModelController {
    
    private static final Logger logger = LoggerFactory.getLogger(LabGlasswareModelController.class);
    
    @GetMapping("/lab-glassware-form")
    public String showForm(Model theModel) {
        LabGlasswareModel labGlasswareModel = new LabGlasswareModel();
        

        logger.info("Joint types: {}", (Object) JointType.values());

        theModel.addAttribute("labGlasswareModel", labGlasswareModel);
        theModel.addAttribute("jointTypes", JointType.values());
        
        return "lab-glassware-form";
    }
    
    @PostMapping("/processLabGlasswareForm")
    public String processLabGlasswareSaveForm (
            @ModelAttribute("labGlasswareModel") LabGlasswareModel labGlasswareModel){
        
        logger.info("labGlasswareModel: {}", labGlasswareModel);
        return "lab-glassware-confirmation";
    }
}
