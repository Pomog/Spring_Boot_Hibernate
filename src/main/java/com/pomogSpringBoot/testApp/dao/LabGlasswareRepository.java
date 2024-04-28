package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabGlasswareRepository extends JpaRepository<LabGlassware, Long> {
}
