package com.BuildMyPC.msvc_build_service.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "compatibility-service", url = "localhost:8088")
public interface CompatibilityClient {
    @PostMapping("/api/v1/compatibilities/validar")
    Map<String, Object> validarBuild(@RequestBody Map<String, Object> build);
}
