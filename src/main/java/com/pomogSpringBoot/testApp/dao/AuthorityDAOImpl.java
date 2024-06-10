package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO{
    private final EntityManager entityManager;
    
    public AuthorityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Authority> findAuthorityByName(String theAuthorityName) {
        TypedQuery<Authority> theQuery = entityManager.createQuery("from Authority where authority=:roleName", Authority.class);
        theQuery.setParameter("roleName", theAuthorityName);
        
        List<Authority> theAuthority = new ArrayList<>();
        
        try {
            theAuthority = theQuery.getResultList();
        } catch (Exception e) {
            theAuthority = null;
        }
        
        return theAuthority;
    }
}
