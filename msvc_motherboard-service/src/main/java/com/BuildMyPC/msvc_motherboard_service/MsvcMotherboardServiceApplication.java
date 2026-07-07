package com.BuildMyPC.msvc_motherboard_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcMotherboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcMotherboardServiceApplication.class, args);
	}

}
