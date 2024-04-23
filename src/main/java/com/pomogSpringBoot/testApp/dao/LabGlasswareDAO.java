package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;

import java.util.List;

public interface LabGlasswareDAO {
    LabGlassware save (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Integer id);
    List<LabGlassware> findAllLabGlassware();
    List<LabGlassware> findLabGlasswareByName(String name);
    void deleteByID (Integer id);
    int deleteAllGlassJoint();
}
