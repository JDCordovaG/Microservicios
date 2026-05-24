package com.grupo_4.MSVC_placa_madre.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Placa_madreDTO {

    @NotNull(message = "El ID del componente es obligatorio")
    private Long componenteId;

    @NotBlank(message = "El socket es obligatorio")
    private String socket;

    @NotBlank(message = "El chipset es obligatorio")
    private String chipset;

    @NotBlank(message = "El tipo de RAM soportada es obligatorio")
    private String tipoRamSoportada;

    @NotNull(message = "Debe indicar la cantidad de slots de RAM")
    @Positive(message = "Los slots de RAM deben ser mayores a 0")
    private Integer slotsRam;

    @NotNull(message = "Debe indicar la capacidad máxima de RAM")
    @Positive(message = "La capacidad máxima de RAM debe ser positiva")
    private Integer maxRamGb;

    @NotBlank(message = "El formato (ATX, Micro-ATX, etc.) es obligatorio")
    private String formato;

}
