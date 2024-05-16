package com.pomogSpringBoot.testApp.service.dbService;

import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.LabGlassware;

import java.util.List;
public interface LabGlasswareService {
    LabGlasswareDTO saveUsingDAO (LabGlassware labGlassware);
    LabGlasswareDTO save (LabGlassware labGlassware);
    LabGlassware update (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Long id);
    List<LabGlasswareDTO> findAllLabGlassware();
    List<LabGlasswareDTO> findLabGlasswareByName(String name);
    void deleteByID (Long id);
    int deleteAllGlassJoint();
}