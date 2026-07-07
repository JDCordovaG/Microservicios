package com.BuildMyPC.msvc_cpu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcCpuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCpuServiceApplication.class, args);
	}

}
