package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO{
    private final EntityManager entityManager;
    
    public AuthorityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Authority findAuthorityByName(String theAuthorityName) {
        TypedQuery<Authority> theQuery = entityManager.createQuery("from Authority where authority=:roleName", Authority.class);
        theQuery.setParameter("roleName", theAuthorityName);
        
        Authority theAuthority = null;
        
        try {
            theAuthority = theQuery.getSingleResult();
        } catch (Exception e) {
            theAuthority = null;
        }
        
        return theAuthority;
    }
}
