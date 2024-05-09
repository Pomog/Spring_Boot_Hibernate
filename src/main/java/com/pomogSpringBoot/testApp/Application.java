package com.pomogSpringBoot.testApp;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(LabGlasswareDAO labGlasswareDAO){
		return  args -> {

		};
	}
}
