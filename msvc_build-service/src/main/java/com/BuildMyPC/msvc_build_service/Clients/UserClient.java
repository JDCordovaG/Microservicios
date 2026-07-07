package com.BuildMyPC.msvc_build_service.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "localhost:8091")
public interface UserClient {
    @GetMapping("/api/v1/users/{id}")
    Object obtenerUsuario(@PathVariable Long id);
}
