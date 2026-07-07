package com.BuildMyPC.msvc_gpu_service.Clients;

import com.BuildMyPC.msvc_gpu_service.Models.Dtos.ComponenteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-component-service", url = "localhost:8089/api/v1/components")
public interface ComponentClient {

    @GetMapping("/{id}")
    ComponenteDTO obtenerComponentePorId(@PathVariable("id") Long id);
}