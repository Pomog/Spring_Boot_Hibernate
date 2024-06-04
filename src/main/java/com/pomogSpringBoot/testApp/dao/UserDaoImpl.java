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
}
