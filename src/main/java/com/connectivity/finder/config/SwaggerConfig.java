package com.connectivity.finder.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/* This class integrates swagger into the project */

@Configuration
public class SwaggerConfig {
	
	private static final String API_DESC = "Given a text file with comma separated component pairs, this API informs whether "
			+ "two components are connected. Components can be Cities , States or anything that can be represented as a Graph"
			+ "data structure";

	
	
	/**
	 *  This method will create a Docket bean in a Spring Boot configuration to configure Swagger 2 for the application. 
	 *  A Springfox Docket instance provides the primary API configuration with sensible defaults and convenience methods 
	 *  for configuration
	 * 
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.connectivity.finder")).paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	/**
	 * Additional information about the API
	 * @return
	 */
	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "Connectivity Finder API", 
	       API_DESC, 
	       "Open Source", 
	       "Terms of service (None) ", 
	       new Contact("Naresh Tatikonda", "", "nareshb.tatikonda@gmail.com"), 
	       "License of API", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
	
	
}
