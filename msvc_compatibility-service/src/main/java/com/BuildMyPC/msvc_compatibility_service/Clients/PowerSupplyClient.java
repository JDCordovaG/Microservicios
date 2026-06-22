package com.BuildMyPC.msvc_compatibility_service.Clients;

import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.PowerSupplyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "power-supply-service", url = "localhost:8084")
public interface PowerSupplyClient {
    @GetMapping("/api/v1/fuentes_poder/{id}")
    PowerSupplyDTO findById(@PathVariable("id") Long id);
}
