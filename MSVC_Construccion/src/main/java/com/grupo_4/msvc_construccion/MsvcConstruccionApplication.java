package com.grupo_4.msvc_construccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcConstruccionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcConstruccionApplication.class, args);
    }

}
