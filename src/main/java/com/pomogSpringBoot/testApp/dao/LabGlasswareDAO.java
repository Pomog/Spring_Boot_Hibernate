package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;

public interface LabGlasswareDAO {
    void save (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Integer id);
}
