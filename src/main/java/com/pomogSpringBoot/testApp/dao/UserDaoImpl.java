package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import com.pomogSpringBoot.testApp.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;
    public UserDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    @Override
    public User findByUserName(String theUserName) {
        TypedQuery<User> theQuery = entityManager.createQuery("from User where username=:uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }
    
    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> findAllQuery = entityManager.createQuery("FROM User ", User.class);
        
        return findAllQuery.getResultList();
    }
    
    @Override
    @Transactional
    public void addUser(User newUser) {
        entityManager.persist(newUser);
    }
}
