package com.BuildMyPC.msvc_compatibility_service.Clients;

import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.RamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ram-service", url = "localhost:8090")
public interface RamClient {
    @GetMapping("/api/v1/rams/{id}")
    RamDTO findById(@PathVariable("id") Long id);
}