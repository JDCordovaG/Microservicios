package com.BuildMyPC.msvc_gpu_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GpuDTO {

    private Long id;

    @NotNull(message = "El ID del componente base es obligatorio")
    private Long componenteId;

    @NotNull(message = "La cantidad de VRAM es obligatoria")
    @Positive(message = "La VRAM debe ser mayor a 0")
    private Integer vramGb;

    @NotBlank(message = "El tipo de memoria no puede estar vacío")
    private String tipoMemoria;

    @NotNull(message = "El TDP es obligatorio")
    @Positive(message = "El TDP (consumo energético) debe ser mayor a 0")
    private Integer tdpWatts;

    @NotNull(message = "El largo físico (mm) es obligatorio")
    @Positive(message = "El largo de la GPU debe ser positivo para calcular colisiones en el gabinete")
    private Double largoMm;

    @NotNull(message = "El puntaje base de rendimiento es obligatorio")
    private Integer puntajeBase;

    @NotBlank(message = "El fabricante del chip (NVIDIA, AMD, Intel) es obligatorio")
    private String fabricanteChip;

    private Boolean activo;
}