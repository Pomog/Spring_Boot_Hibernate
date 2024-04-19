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
    public void save(LabGlassware labGlassware) {
        entityManager.persist(labGlassware);
    }
    
    @Override
    public LabGlassware findLabGlasswareByID(Integer id) {
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
    @Transactional
    public void update(LabGlassware labGlassware) {
            entityManager.merge(labGlassware);
    }
    
    @Override
    @Transactional
    public void deleteByID(Integer id) {
        LabGlassware labGlassware = entityManager.find(LabGlassware.class, id);
        
        entityManager.remove(labGlassware);
    }
    
    @Override
    @Transactional
    public int deleteAll() {
        
        entityManager.createQuery("DELETE FROM GlassJoint").executeUpdate();
        
        int deletedRows = entityManager.createQuery(
                "DELETE FROM LabGlassware ").executeUpdate();
        return deletedRows;
    }
}
