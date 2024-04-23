package com.pomogSpringBoot.testApp.service;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LabGlasswareServiceImpl implements LabGlasswareService{
    private final LabGlasswareDAO labGlasswareDAO;
    
    @Autowired
    public LabGlasswareServiceImpl(LabGlasswareDAO labGlasswareDAO) {
        this.labGlasswareDAO = labGlasswareDAO;
    }
    
    @Override
    @Transactional
    public LabGlassware save(LabGlassware labGlassware) {
       return this.labGlasswareDAO.save(labGlassware);
    }
    
    @Override
    public LabGlassware findLabGlasswareByID(Integer id) {
        return this.labGlasswareDAO.findLabGlasswareByID(id);
    }
    
    @Override
    public List<LabGlassware> findAllLabGlassware() {
        return this.labGlasswareDAO.findAllLabGlassware();
    }
    
    @Override
    public List<LabGlassware> findLabGlasswareByName(String name) {
        return this.labGlasswareDAO.findLabGlasswareByName(name);
    }
    
    @Override
    @Transactional
    public void deleteByID(Integer id) {
        this.labGlasswareDAO.deleteByID(id);
    }
    
    @Override
    @Transactional
    public int deleteAllGlassJoint() {
        return this.labGlasswareDAO.deleteAllGlassJoint();
    }
}
