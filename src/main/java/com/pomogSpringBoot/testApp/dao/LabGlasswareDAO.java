package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;

import java.util.List;

public interface LabGlasswareDAO {
    void save (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Integer id);
    List<LabGlassware> findAllLabGlassware();
    
    List<LabGlassware> findLabGlasswareByName(String name);
}
