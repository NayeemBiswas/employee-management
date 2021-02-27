/**
 * @Since Feb 27, 2021
 * @Author Nayeem Biswas
 * @Project employee-management
 * @Package com.nayeem.employee.management.config
 */
package com.nayeem.employee.management.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Nayeem
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.nayeem.employee.management.controller"))
				.paths(PathSelectors.any()).build()
						.apiInfo(apiInfo());
				
	}
	
	@Bean
	public SecurityScheme apiKey()
	{
		return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
	}
	
	@Bean
	public SecurityScheme apiCookiueKey()
	{
		return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("ATI MICROSERVICE")
				.description("Author by Nayeemul Islam")
				.termsOfServiceUrl("https://www.facebook.com/nayeembis/")
				.contact(new Contact("Nayeemul Islam", "https://www.facebook.com/nayeembis/", "inayeeme@gmail.com"))
				.license("Open Source").licenseUrl("https://www.facebook.com/nayeembis/").version("1.0.0").build();

	}


}
