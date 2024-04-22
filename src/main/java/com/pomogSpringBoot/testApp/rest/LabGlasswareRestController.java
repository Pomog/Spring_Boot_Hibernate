package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LabGlasswareRestController {
    private final LabGlasswareDAO labGlasswareDAO;
    
    public LabGlasswareRestController(LabGlasswareDAO labGlasswareDAO) {
        this.labGlasswareDAO = labGlasswareDAO;
    }
    
    @GetMapping("/labglassware")
    public List<LabGlassware> getLabGlassware (){
        return labGlasswareDAO.findAllLabGlassware();
    }
    
    @GetMapping("/labglassware/{id}")
    public LabGlassware getLabglasswareByID(@PathVariable int id){
        return null;
    }
    
}
