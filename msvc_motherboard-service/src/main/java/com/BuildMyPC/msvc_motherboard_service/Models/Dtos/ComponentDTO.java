package com.BuildMyPC.msvc_motherboard_service.Models.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComponentDTO {
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
    private Boolean activo;
}