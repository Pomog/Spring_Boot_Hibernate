package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository  {
    public Status findByName(String name);
    
    void save(Status status);
}
