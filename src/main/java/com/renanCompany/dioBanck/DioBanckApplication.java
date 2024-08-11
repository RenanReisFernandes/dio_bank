package com.renanCompany.dioBanck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API DIOBANK", version = "1.0.0", description = "API of challenge for DIO"))
public class DioBanckApplication {

	public static void main(String[] args) {
		SpringApplication.run(DioBanckApplication.class, args);
	}

}
