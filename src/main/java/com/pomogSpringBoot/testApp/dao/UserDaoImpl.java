package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;
    public UserDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    @Override
    public User findByUserName(String theUserName) {
        // retrieve/read from database using username
        TypedQuery<User> theQuery = entityManager.createQuery("from User where username=:uName and enabled=true", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }
}
