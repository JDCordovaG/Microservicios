package com.BuildMyPC.msvc_compatibility_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompatibilityDTO {

    @NotNull(message = "El ID de la build es obligatorio")
    private Long buildId;

    @NotBlank(message = "El campo compatible no puede estar vacio")
    private String compatible;

    @NotNull(message = "El campo consumo Estimado de Watts no puede estar vacio")
    private Integer consumoEstimadoWatts;

    @NotNull(message = "El campo margen de Fuente no puede estar vacio")
    private String margenFuente;

    @NotNull(message = "El campo observaciones es obligatoria")
    private String observaciones;

    @NotNull(message = "La fecha de Validacion es obligatoria")
    private LocalDate fechaValidacion;

}
