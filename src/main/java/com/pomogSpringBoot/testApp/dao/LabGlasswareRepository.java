package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "labGlassware", path = "labGlasswareRest")
public interface   LabGlasswareRepository extends JpaRepository<LabGlassware, Long> {
}
