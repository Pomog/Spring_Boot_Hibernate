package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.Authority;

import java.util.List;

public interface AuthorityDAO {
    public List<Authority> findAuthorityByName(String theAuthorityName);
}
