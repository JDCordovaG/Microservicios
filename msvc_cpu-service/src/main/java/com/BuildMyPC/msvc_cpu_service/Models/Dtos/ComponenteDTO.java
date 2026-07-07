package com.BuildMyPC.msvc_cpu_service.Models.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComponenteDTO {
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
    private Boolean activo;
}