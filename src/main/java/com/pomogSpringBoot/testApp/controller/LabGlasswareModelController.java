package com.pomogSpringBoot.testApp.controller;

import com.pomogSpringBoot.testApp.entity.JointType;
import model.GlassJointModel;
import model.LabGlasswareModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LabGlasswareModelController {
    
    @GetMapping("/lab-glassware-form")
    public String showForm (Model theModel){
        LabGlasswareModel labGlasswareModel = new LabGlasswareModel();
        GlassJointModel glassJointModel = new GlassJointModel();
        labGlasswareModel.getGlassJoints().add(glassJointModel);
        
        theModel.addAttribute("jointTypes", JointType.values());
        
        theModel.addAttribute("labGlasswareModel", labGlasswareModel);
        
        return "lab-glassware-form";
    }
}
