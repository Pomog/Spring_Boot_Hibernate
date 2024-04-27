package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LabGlasswareDAOImpl implements LabGlasswareDAO{
    private final EntityManager entityManager;
    
    @Autowired
    public LabGlasswareDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public LabGlassware save (LabGlassware labGlassware) {
        return entityManager.merge(labGlassware);
    }
    
    @Override
    public LabGlassware findLabGlasswareByID(Long id) {
        return entityManager.find(LabGlassware.class, id);
    }
    
    @Override
    public List<LabGlassware> findAllLabGlassware() {
        TypedQuery<LabGlassware> findAllQuery = entityManager.createQuery("FROM LabGlassware", LabGlassware.class);
        
        return findAllQuery.getResultList();
    }
    
    @Override
    public List<LabGlassware> findLabGlasswareByName(String name) {
        TypedQuery<LabGlassware> findByNameQuery = entityManager.createQuery(
                "FROM LabGlassware WHERE name LIKE :name", LabGlassware.class);
        findByNameQuery.setParameter("name", name);
        
        return findByNameQuery.getResultList();
    }
    
    @Override
    public void deleteByID(Long id) {
        entityManager.remove(entityManager.find(LabGlassware.class, id));
    }
    
    @Override
    public int deleteAllGlassJoint() {
        return entityManager.createQuery(
                "DELETE FROM LabGlassware ").executeUpdate();
    }
}
