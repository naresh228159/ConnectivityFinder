package com.connectivity.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * SpringBootApplication class for the Connectivity Finder Application
 * @author Naresh Tatikonda
 */

@SpringBootApplication
@EnableSwagger2
public class ConnectivityFinderApplication {

	/**
	 * This is the main class of our application form which the Spring Boot application will get executed
	 * @param args
	 */
	public static void main(String[] args) {
		/* Bootstraps a spring application as a stand-alone application from the main method. 
		 * It creates an appropriate ApplicationContext instance and load beans.
		 */
		SpringApplication.run(ConnectivityFinderApplication.class, args);
	}

}
