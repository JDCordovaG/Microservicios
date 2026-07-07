package com.BuildMyPC.msvc_compatibility_service.Models.Dtos;

import lombok.Data;

@Data
public class MotherboardDTO {
    private Long id;
    private String socket;
    private String tipoRamSoportada;
    private Integer maxRamGb;
}