package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabGlasswareDAO {
    LabGlassware save (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Long id);
    List<LabGlassware> findAllLabGlassware();
    List<LabGlassware> findLabGlasswareByName(String name);
    void deleteByID (Long id);
    int deleteAllGlassJoint();
}
