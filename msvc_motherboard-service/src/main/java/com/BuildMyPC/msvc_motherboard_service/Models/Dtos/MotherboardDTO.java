package com.BuildMyPC.msvc_motherboard_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MotherboardDTO {

    private Long id;

    @NotNull(message = "El ID del componente es obligatorio")
    private Long componenteId;

    @NotBlank(message = "El socket es obligatorio")
    private String socket;

    @NotBlank(message = "El chipset es obligatorio")
    private String chipset;

    @NotBlank(message = "El tipo de RAM soportada es obligatorio (ej. DDR4, DDR5)")
    private String tipoRamSoportada;

    @NotNull(message = "Debe indicar la cantidad de slots de RAM")
    @Positive(message = "Los slots de RAM deben ser mayores a 0")
    private Integer slotsRam;

    @NotNull(message = "Debe indicar la capacidad máxima de RAM")
    @Positive(message = "La capacidad máxima de RAM debe ser positiva")
    private Integer maxRamGb;

    @NotBlank(message = "El formato (ATX, Micro-ATX, Mini-ITX) es obligatorio")
    private String formato;

    private Boolean activo;
}