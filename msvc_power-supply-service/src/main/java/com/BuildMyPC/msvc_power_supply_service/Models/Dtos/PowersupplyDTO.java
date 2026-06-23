package com.BuildMyPC.msvc_power_supply_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PowersupplyDTO {

    private Long id;

    @NotNull(message = "El ID del componente es obligatorio")
    private Long componenteId;

    @NotNull(message = "Debe indicar la potencia en Watts")
    @Positive(message = "La potencia debe ser mayor a 0")
    private Integer potenciaWatts;

    @NotBlank(message = "La certificación es obligatoria (Ej: 80 Plus Gold, Genérica)")
    private String certificacion;

    @NotBlank(message = "El formato de la fuente es obligatorio (ej. ATX, SFX)")
    private String formato;

    @NotNull(message = "Debe indicar si la fuente es modular (true/false)")
    private Boolean esModular;

    private Boolean activo;
}