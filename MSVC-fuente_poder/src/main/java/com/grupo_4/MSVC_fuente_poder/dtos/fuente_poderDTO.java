package com.grupo_4.MSVC_fuente_poder.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data

public class fuente_poderDTO {

    @NotNull(message = "El ID del componente es obligatorio")
    private Long componenteId;

    @NotNull(message = "Debe indicar la potencia en Watts")
    @Positive(message = "La potencia debe ser mayor a 0")
    private Integer potenciaWatts;

    @NotBlank(message = "La certificación es obligatoria")
    private String certificacion;

    @NotBlank(message = "El formato de la fuente es obligatorio (ej. ATX)")
    private String formato;

    @NotNull(message = "Debe indicar si es modular (true/false)")
    private Boolean esModular;
}
