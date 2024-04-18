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

//			createLabGlassware(labGlasswareDAO);
			createMultipleLabGlassware(labGlasswareDAO);
		};
	
	}
	
	private void createMultipleLabGlassware(LabGlasswareDAO labGlasswareDAO) {
		System.out.println("Creating 3 LabGlasswares");
		var glassJoint1 = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
		var glassJoint2 = new GlassJoint(JointType.SPHERICAL_CUP, "S41");
		var glassJoint3 = new GlassJoint(JointType.CONE_SOCKET, "29/32");
		var labGlassware1 = new LabGlassware("Flask");
		var labGlassware2 = new LabGlassware("Flask");
		var labGlassware3 = new LabGlassware("Flask");
		labGlassware1.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
		labGlassware2.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
		labGlassware3.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
		labGlassware1.addGlassJoint(glassJoint1);
		labGlassware2.addGlassJoint(glassJoint2);
		labGlassware2.addGlassJoint(glassJoint3);
		
		labGlasswareDAO.save(labGlassware1);
		labGlasswareDAO.save(labGlassware2);
		labGlasswareDAO.save(labGlassware3);
	}
	
	private void createLabGlassware(LabGlasswareDAO labGlasswareDAO) {
		System.out.println("Creating test LabGlassware");
		var glassJoint = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
		var labGlassware = new LabGlassware("Flask");
		labGlassware.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
		labGlassware.addGlassJoint(glassJoint);
		
		System.out.println("Saving test LabGlassware");
		labGlasswareDAO.save(labGlassware);
		
		System.out.println("Display ID of the saved labGlassware");
		System.out.println(labGlassware.getId());
		
		System.out.println("Display ID of the saved glassJoint and corresponds labGlassware ID");
		System.out.println(glassJoint.getId());
		System.out.println(glassJoint.getLabGlassware().getId());
	}
}
