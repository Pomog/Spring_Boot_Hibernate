package com.pomogSpringBoot.testApp.service;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabGlasswareServiceImpl implements LabGlasswareService{
    private final LabGlasswareDAO labGlasswareDAO;
    
    @Autowired
    public LabGlasswareServiceImpl(LabGlasswareDAO labGlasswareDAO) {
        this.labGlasswareDAO = labGlasswareDAO;
    }
    
    @Override
    public List<LabGlassware> findAll() {
        return labGlasswareDAO.findAllLabGlassware();
    }
}
