package com.BuildMyPC.msvc_benchmark_service.Clients;

import com.BuildMyPC.msvc_benchmark_service.Models.Dtos.GpuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gpu-service", url = "localhost:8082")
public interface GpuClient {
    @GetMapping("/api/v1/gpus/{id}")
    GpuDTO getGpuById(@PathVariable("id") Long id);
}
