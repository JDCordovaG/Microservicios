package com.BuildMyPC.msvc_benchmark_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BenchmarkDTO {

    @NotNull(message = "El ID de la build es obligatorio")
    private Long buildId;

    @NotNull(message = "El campo puntaje Cpu no puede estar vacio")
    private Integer puntajeCpu;

    @NotNull(message = "El campo puntaje Gpu no puede estar vacio")
    private Integer puntajeGpu;

    @NotNull(message = "El campo puntaje Total no puede estar vacio")
    private Integer puntajeTotal;

    @NotBlank(message = "La categoria de Uso es obligatoria")
    private String categoriaUso;

    @NotNull(message = "La fecha de Calculo es obligatoria")
    private LocalDate fechaCalculo;

}
