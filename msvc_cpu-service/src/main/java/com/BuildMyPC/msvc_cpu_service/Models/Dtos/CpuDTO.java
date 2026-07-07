package com.BuildMyPC.msvc_cpu_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CpuDTO {

    private Long id;

    @NotNull(message = "El ID del componente base es obligatorio")
    private Long componenteId;

    @NotBlank(message = "El socket del procesador es obligatorio")
    private String socket;

    @Positive(message = "La cantidad de núcleos debe ser mayor a cero")
    private Integer nucleos;

    @Positive(message = "La cantidad de hilos debe ser mayor a cero")
    private Integer hilos;

    @Positive(message = "La frecuencia base debe ser un valor positivo")
    private Double frecuenciaBase;

    private Double frecuenciaTurbo;

    @NotNull(message = "El TDP en Watts es obligatorio")
    @Positive(message = "El TDP (Consumo energético) debe ser positivo")
    private Integer tdpWatts;

    @NotBlank(message = "La generación del procesador es obligatoria")
    private String generacion;

    private Boolean soportaDdr4;
    private Boolean soportaDdr5;
    private Boolean activo;
}
