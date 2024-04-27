package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.rest.errorRespose.LabGlasswareNotFoundException;
import com.pomogSpringBoot.testApp.service.LabGlasswareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LabGlasswareRestController {
    private final LabGlasswareService labGlasswareService;
    
    @Autowired
    public LabGlasswareRestController(LabGlasswareService labGlasswareService) {
        this.labGlasswareService = labGlasswareService;
    }
    
    @GetMapping("/labglassware")
    public List<LabGlassware> getLabGlassware() {
        return labGlasswareService.findAllLabGlassware();
    }
    
    @GetMapping("/labglassware/{id}")
    public LabGlassware getLabglasswareByID(@PathVariable Long id) {
        var labGlassware = labGlasswareService.findLabGlasswareByID(id);
        if (labGlassware == null) {
            throw new LabGlasswareNotFoundException("Not found item with id: " + id);
        }
        return labGlassware;
    }
    
    @PostMapping("/labglassware")
    public LabGlassware addLabGlassware(@RequestBody LabGlassware labGlassware) {
        return labGlasswareService.save(labGlassware);
    }
    
    @PutMapping("/labglassware")
    public LabGlassware updateLabGlassware(@RequestBody LabGlassware labGlassware) {
        if (labGlassware.getId() == null) {
            throw new LabGlasswareNotFoundException("ID cannot be empty");
        } else {
            LabGlassware existingLabGlassware = labGlasswareService.findLabGlasswareByID(labGlassware.getId());
            if (existingLabGlassware == null) {
                throw new LabGlasswareNotFoundException("LabGlassware not found");
            }
        }
        return labGlasswareService.save(labGlassware);
    }
    
    @DeleteMapping("/labglassware/{id}")
    public String deleteLabGlasswareByID(@PathVariable long id) {
        var foundLabGlassware = labGlasswareService.findLabGlasswareByID(id);
        if (foundLabGlassware == null) {
            throw new LabGlasswareNotFoundException("LabGlassware not found");
        }
        labGlasswareService.deleteByID(id);
        return "LabGlassware with id: " + id + " was deleted.";
    }
    
}
