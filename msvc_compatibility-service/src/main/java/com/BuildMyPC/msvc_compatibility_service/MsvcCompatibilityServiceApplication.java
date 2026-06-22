package com.BuildMyPC.msvc_compatibility_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcCompatibilityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCompatibilityServiceApplication.class, args);
	}
}