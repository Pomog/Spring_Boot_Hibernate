package com.pomogSpringBoot.testApp.service;

import com.pomogSpringBoot.testApp.entity.LabGlassware;

import java.util.List;

public interface LabGlasswareService {
    LabGlassware save (LabGlassware labGlassware);
    LabGlassware update (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Long id);
    List<LabGlassware> findAllLabGlassware();
    List<LabGlassware> findLabGlasswareByName(String name);
    void deleteByID (Long id);
    int deleteAllGlassJoint();;
}
