package com.grupo_4.MSVC_GPU.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GpuDTO {

    @NotNull(message = "El ID del componente es obligatorio")
    private Long componenteId;

    @NotNull(message = "La cantidad de VRAM es obligatoria")
    @Positive(message = "La VRAM debe ser mayor a 0")
    private Integer vramGb;

    @NotBlank(message = "El tipo de memoria no puede estar vacío")
    private String tipoMemoria;

    @NotNull(message = "El TDP es obligatorio")
    @Positive(message = "El TDP debe ser mayor a 0")
    private Integer tdpWatts;

    @NotNull(message = "El largo físico (mm) es obligatorio")
    @Positive(message = "El largo de la GPU debe ser positivo")
    private Double largoMm;

    @NotNull(message = "El puntaje base es obligatorio")
    private Integer puntajeBase;

    @NotBlank(message = "El fabricante del chip es obligatorio")
    private String fabricanteChip;
}
