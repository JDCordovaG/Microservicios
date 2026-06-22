package com.BuildMyPC.msvc_benchmark_service.Clients;

import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.CpuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpu-service", url = "localhost:8081")
public interface CpuClient {
    @GetMapping("/api/v1/cpus/{id}")
    CpuDTO getCpuById(@PathVariable("id") Long id);
}
