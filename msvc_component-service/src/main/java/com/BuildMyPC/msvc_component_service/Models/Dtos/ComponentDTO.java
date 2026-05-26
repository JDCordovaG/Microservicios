package com.BuildMyPC.msvc_component_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ComponentDTO {

    @NotNull(message = "El tipo del componente es obligatorio")
    private String tipo;

    @NotBlank(message = "El campo marca no puede estar vacio")
    private String marca;

    @NotNull(message = "El campo modelo no puede estar vacio")
    private String modelo;

    @NotNull(message = "El campo precio Base no puede estar vacio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Integer precioBase;

    @NotNull(message = "El estado es obligatoria")
    private String estado;

    @NotNull(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "El campo fecha de Lanzamiento no puede estar vacio")
    private LocalDate fechaLanzamiento;

}
