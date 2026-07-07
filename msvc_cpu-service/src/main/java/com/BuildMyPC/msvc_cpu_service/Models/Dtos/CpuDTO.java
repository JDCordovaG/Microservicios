package com.BuildMyPC.msvc_cpu_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CpuDTO {

    @NotNull(message = "El ID del componente es obligatorio")
    private Long componenteId;

    @NotBlank(message = "El campo socket no puede estar vacio")
    private String socket;

    @NotNull(message = "El campo núcleos no puede estar vacio")
    @Positive(message = "Los núcleos deben ser mayores a 0")
    private Integer nucleos;

    @NotNull(message = "El campo hilos no puede estar vacio")
    private Integer hilos;

    @NotNull(message = "La frecuencia base es obligatoria")
    private Double frecuenciaBase;

    @NotNull(message = "La frecuencia turbo es obligatoria")
    private Double frecuenciaTurbo;

    @NotNull(message = "El campo tdp watts no puede estar vacio")
    @Positive(message = "El TDP debe ser mayor a 0")
    private Integer tdpWatts;

    @NotNull(message = "Debe indicar si soporta DDR4")
    private Boolean soportaDdr4;

    @NotNull(message = "Debe indicar si soporta DDR5")
    private Boolean soportaDdr5;

}
