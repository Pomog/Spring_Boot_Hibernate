package com.pomogSpringBoot.testApp.service;

import com.pomogSpringBoot.testApp.entity.LabGlassware;

import java.util.List;

public interface LabGlasswareService {
    LabGlassware save (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Integer id);
    List<LabGlassware> findAllLabGlassware();
    List<LabGlassware> findLabGlasswareByName(String name);
    void deleteByID (Integer id);
    int deleteAllGlassJoint();;
}
