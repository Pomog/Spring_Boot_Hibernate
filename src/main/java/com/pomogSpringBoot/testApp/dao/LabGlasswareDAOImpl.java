package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LabGlasswareDAOImpl implements LabGlasswareDAO{
    private EntityManager entityManager;
    
    @Autowired
    public LabGlasswareDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public void save(LabGlassware labGlassware) {
        entityManager.persist(labGlassware);
    }
    
    @Override
    @Transactional
    public LabGlassware findLabGlasswareByID(Integer id) {
        return entityManager.find(LabGlassware.class, id);
    }
}
