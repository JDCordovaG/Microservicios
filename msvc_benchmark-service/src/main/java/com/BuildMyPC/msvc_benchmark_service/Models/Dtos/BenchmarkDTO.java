package com.BuildMyPC.msvc_benchmark_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BenchmarkDTO {
    @NotNull(message = "El ID de la build es obligatorio")
    private Long buildId;

    @NotBlank(message = "La categoría de uso es obligatoria")
    private String categoriaUso;
}
