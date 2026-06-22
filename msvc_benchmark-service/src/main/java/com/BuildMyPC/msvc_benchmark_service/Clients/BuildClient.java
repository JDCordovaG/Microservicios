package com.BuildMyPC.msvc_benchmark_service.Clients;

import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.BuildDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "build-service", url = "localhost:8087")
public interface BuildClient {
    @GetMapping("/api/v1/builds/{id}")
    BuildDTO getBuildById(@PathVariable("id") Long id);
}
