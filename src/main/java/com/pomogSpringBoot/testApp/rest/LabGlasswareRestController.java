package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.service.LabGlasswareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LabGlasswareRestController {
    private LabGlasswareService labGlasswareService;
    
    @Autowired
    public LabGlasswareRestController(LabGlasswareService labGlasswareService) {
        this.labGlasswareService = labGlasswareService;
    }
    
    @GetMapping("/labglassware")
    public List<LabGlassware> getLabGlassware (){
        return labGlasswareService.findAll();
    }
    
    @GetMapping("/labglassware/{id}")
    public LabGlassware getLabglasswareByID(@PathVariable int id){
        return null;
    }
    
}
