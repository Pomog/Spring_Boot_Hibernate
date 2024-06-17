package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import com.pomogSpringBoot.testApp.errorRespose.LabGlasswareException;
import com.pomogSpringBoot.testApp.service.dbService.LabGlasswareService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> getImageLabGlasswareByID(@PathVariable Long id) {
        LabGlassware labGlassware = labGlasswareService.findLabGlasswareByID(id);
        if (labGlassware != null) {
            byte[] imageBytes = java.util.Base64.getDecoder().decode(labGlassware.getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/labglasswareName/{name}")
    public List<LabGlasswareDTO> getLabglasswareByName(@PathVariable String name) {
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
    
    @PostMapping("/labglassware-img")
    public LabGlasswareDTO addLabGlasswareIMG(@RequestBody @NonNull LabGlassware labGlassware, @RequestParam("imageFile") MultipartFile imageFile) {
        return labGlasswareService.saveWithImage(labGlassware, imageFile);
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
