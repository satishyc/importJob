package com.satish.importJob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImportJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportJobApplication.class, args);
	}

}
