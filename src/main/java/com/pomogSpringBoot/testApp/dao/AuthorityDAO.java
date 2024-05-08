package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.user.Authority;

public interface AuthorityDAO {
    public Authority findAuthorityByName(String theAuthorityName);
}
