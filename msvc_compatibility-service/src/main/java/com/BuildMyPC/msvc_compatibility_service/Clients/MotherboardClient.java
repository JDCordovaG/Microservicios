package com.BuildMyPC.msvc_compatibility_service.Clients;

import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.MotherboardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "motherboard-service", url = "localhost:8083")
public interface MotherboardClient {
    @GetMapping("/api/v1/placas_madre/{id}")
    MotherboardDTO findById(@PathVariable("id") Long id);
}