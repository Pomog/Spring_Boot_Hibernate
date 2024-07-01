package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.Status;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StatusRepositoryImpl implements StatusRepository{
    
    private final EntityManager entityManager;
    
    @Autowired
    public StatusRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Status findByName(String name) {
        TypedQuery<Status> findByNameQuery = entityManager.createQuery(
                "FROM Status WHERE name LIKE :name", Status.class);
        findByNameQuery.setParameter("name", name);
        
        try {
            return findByNameQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public void save(Status status) {
        entityManager.persist(status);
    }
}
