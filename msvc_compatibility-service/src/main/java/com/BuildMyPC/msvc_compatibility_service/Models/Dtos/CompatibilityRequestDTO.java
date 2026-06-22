package com.BuildMyPC.msvc_compatibility_service.Models.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompatibilityRequestDTO {

    @NotNull(message = "El ID de la build es obligatorio")
    private Long buildId;

    @NotNull(message = "El ID de CPU es obligatorio")
    private Long cpuId;

    @NotNull(message = "El ID de GPU es obligatorio")
    private Long gpuId;

    @NotNull(message = "El ID de la Motherboard es obligatorio")
    private Long motherboardId;

    @NotNull(message = "El ID de RAM es obligatorio")
    private Long ramId;

    @NotNull(message = "El ID de la fuente es obligatorio")
    private Long fuenteId;
}