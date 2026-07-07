package com.BuildMyPC.msvc_build_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcBuildServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcBuildServiceApplication.class, args);
	}

}
