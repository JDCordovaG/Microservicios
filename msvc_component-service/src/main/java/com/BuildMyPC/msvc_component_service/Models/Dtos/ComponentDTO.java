package com.BuildMyPC.msvc_component_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ComponentDTO {

    @NotBlank(message = "El tipo del componente es obligatorio")
    @Pattern(regexp = "^(CPU|GPU|MOTHERBOARD|RAM|PSU|CASE)$",
            message = "El tipo debe ser uno de los permitidos: CPU, GPU, MOTHERBOARD, RAM, PSU, CASE")
    private String tipo;

    @NotBlank(message = "El campo marca no puede estar vacio")
    private String marca;

    @NotBlank(message = "El campo modelo no puede estar vacio")
    private String modelo;

    @NotNull(message = "El campo precio Base no puede estar vacio")
    @Positive(message = "El precio base debe ser mayor que cero") // Regla del caso semestral
    private Integer precioBase;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "El campo fecha de Lanzamiento no puede estar vacio")
    private LocalDate fechaLanzamiento;
}
