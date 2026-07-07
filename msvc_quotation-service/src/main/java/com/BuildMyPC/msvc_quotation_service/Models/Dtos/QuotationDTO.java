package com.BuildMyPC.msvc_quotation_service.Models.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class QuotationDTO {

    @NotNull(message = "El ID de la build es obligatorio")
    private Long buildId;

    @NotNull(message = "El campo id de usuario no puede estar vacio")
    private Long usuarioId;

    @NotNull(message = "El campo subtotal no puede estar vacio")
    @Positive(message = "El subtotal debe ser mayor a 0")
    private Integer subtotal;

    @NotNull(message = "El campo descuento no puede estar vacio")
    private Integer descuento;

    @NotNull(message = "El total es obligatorio")
    private Double total;

    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El campo fecha de Emision no puede estar vacio")
    private LocalDate fechaEmision;

    @NotNull(message = "El campo fecha de Vencimiento no puede estar vacio")
    private LocalDate fechaVencimiento;

}
