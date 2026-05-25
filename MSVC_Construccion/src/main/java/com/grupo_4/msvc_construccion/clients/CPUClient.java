package com.grupo_4.msvc_construccion.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpu-service", url = "http://localhost:8081/api/v1/cpus")
public interface CPUClient {

    @GetMapping("/{id}")
    Object obtenerCpuPorId(@PathVariable("id") Long id);
}
