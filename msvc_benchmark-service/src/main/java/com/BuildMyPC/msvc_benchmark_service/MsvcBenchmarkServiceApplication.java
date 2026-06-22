package com.BuildMyPC.msvc_benchmark_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // <- 1. AGREGAR ESTA IMPORTACIÓN

@EnableFeignClients
@SpringBootApplication
public class MsvcBenchmarkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcBenchmarkServiceApplication.class, args);
	}

}
