package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.errorRespose.LabGlasswareException;
import com.pomogSpringBoot.testApp.service.LabGlasswareService;
import lombok.NonNull;
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
    public List<LabGlasswareDTO> getLabGlassware() {
        var labGlassware = labGlasswareService.findAllLabGlassware();
        if (labGlassware.isEmpty()) {
            throw new LabGlasswareException("Not found items");
        }
        return labGlassware;
    }
    
    @GetMapping("/labglassware/{id}")
    public LabGlassware getLabglasswareByID(@PathVariable Long id) {
        var labGlassware = labGlasswareService.findLabGlasswareByID(id);
        if (labGlassware == null) {
            throw new LabGlasswareException("Not found item with id: " + id);
        }
        return labGlassware;
    }
    
    @GetMapping("/labglasswareName/{name}")
    public List<LabGlasswareDTO> getLabglasswareByID(@PathVariable String name) {
        var labGlassware = labGlasswareService.findLabGlasswareByName(name);
        if (labGlassware.isEmpty()) {
            throw new LabGlasswareException("Not found items with name: " + name);
        }
        return labGlassware;
    }
    
    @PostMapping("/labglassware")
    public LabGlasswareDTO addLabGlassware(@RequestBody @NonNull LabGlassware labGlassware) {
        return labGlasswareService.saveUsingDAO(labGlassware);
    }
    
    @PutMapping("/labglassware")
    public LabGlasswareDTO updateLabGlassware(@RequestBody @NonNull LabGlassware labGlassware) {
        LabGlassware existingLabGlassware = labGlasswareService.findLabGlasswareByID(labGlassware.getId());
        if (existingLabGlassware == null) {
            throw new LabGlasswareException("LabGlassware not found");
        }
        return labGlasswareService.save(labGlassware);
    }
    
    @DeleteMapping("/labglassware/{id}")
    public String deleteLabGlasswareByID(@PathVariable long id) {
        var foundLabGlassware = labGlasswareService.findLabGlasswareByID(id);
        if (foundLabGlassware == null) {
            throw new LabGlasswareException("LabGlassware not found");
        }
        labGlasswareService.deleteByID(id);
        return "LabGlassware with id: " + id + " was deleted.";
    }
    
}
