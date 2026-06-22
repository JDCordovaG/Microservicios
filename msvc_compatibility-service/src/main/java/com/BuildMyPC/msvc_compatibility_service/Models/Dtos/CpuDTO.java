package com.BuildMyPC.msvc_compatibility_service.Models.Dtos;

import lombok.Data;

@Data
public class CpuDTO {
    private Long id;
    private String socket;
    private Integer tdpWatts;
}