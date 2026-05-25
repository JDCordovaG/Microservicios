package com.grupo_4.msvc_construccion.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConstruccionDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "Debe seleccionar un procesador (CPU)")
    private Long cpuId;

    @NotNull(message = "Debe seleccionar una tarjeta gráfica (GPU)")
    private Long gpuId;

    @NotNull(message = "Debe seleccionar una placa madre")
    private Long motherboardId;

    @NotNull(message = "Debe seleccionar la memoria RAM")
    private Long ramId;

    @NotNull(message = "Debe seleccionar una fuente de poder")
    private Long fuenteId;

}
