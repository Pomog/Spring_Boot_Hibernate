package com.pomogSpringBoot.testApp;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import com.pomogSpringBoot.testApp.entity.GlassJoint;
import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class TestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(LabGlasswareDAO labGlasswareDAO){
		return  args -> {
			System.out.println("CommandLineRunner");
			
			createLabGlassware(labGlasswareDAO);
	
		};
	
	}
	
	private void createLabGlassware(LabGlasswareDAO labGlasswareDAO) {
		System.out.println("Creating test LabGlassware");
		
		var glassJoint = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
		var labGlassware = new LabGlassware("Flask");
		labGlassware.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
//		labGlassware.addGlassJoint(glassJoint);
		
		System.out.println("Saving test LabGlassware");
		labGlasswareDAO.save(labGlassware);
		
		System.out.println("Display ID of the saved labGlassware");
		System.out.println(labGlassware.getId());
	}
}
