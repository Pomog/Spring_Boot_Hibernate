package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;

import java.util.List;

public interface LabGlasswareDAO {
    LabGlassware save (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Long id);
    List<LabGlassware> findAllLabGlassware();
    List<LabGlassware> findLabGlasswareByName(String name);
    List<LabGlassware> brokenAndRepaired(Boolean broken, Boolean repaired);
    void deleteByID (Long id);
    int deleteAllGlassJoint();
    List<LabGlassware> findByVolume(int maxVol, int minVol);
}
