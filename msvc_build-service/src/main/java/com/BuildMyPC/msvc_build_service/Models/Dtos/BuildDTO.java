package com.BuildMyPC.msvc_build_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BuildDTO {

    @NotNull(message = "El ID del componente es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El ID del cpu es obligatorio")
    private Long cpuId;

    @NotNull(message = "El ID del gpu es obligatorio")
    private Long gpuId;

    @NotNull(message = "El ID de la motherboard es obligatorio")
    private Long motherboardId;

    @NotNull(message = "El ID de la ram es obligatorio")
    private Long ramId;

    @NotNull(message = "El ID de la fuente de poder es obligatorio")
    private Long fuenteId;

    @NotBlank(message = "El campo estado no puede estar vacio")
    private String estado;

    @NotNull(message = "El campo fechaCreacion no puede estar vacio")
    private LocalDate fechaCreacion;

    @NotNull(message = "El campo fechaActualizacion no puede estar vacio")
    private LocalDate fechaActualizacion;

}
