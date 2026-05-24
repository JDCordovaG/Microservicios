package com.grupo_4.msvccomponente.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ComponenteDTO {

    @NotBlank(message = "El tipo de componente es obligatorio (Ej: CPU, GPU)")
    private String tipo;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotNull(message = "El precio base es obligatorio")
    @Positive(message = "El precio base debe ser mayor a 0") // Regla del PDF
    private Double precioBase;

    private String descripcion;

    private LocalDate fechaLanzamiento;
}
