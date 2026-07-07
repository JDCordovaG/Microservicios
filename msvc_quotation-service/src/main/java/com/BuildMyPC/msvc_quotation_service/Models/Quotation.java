package com.BuildMyPC.msvc_quotation_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id")
    private Long id;

    @Column(nullable = false, name = "build_id_quotation")
    private Long buildId;

    @Column(nullable = false, name = "usuario_id_quotation")
    private Long usuarioId;

    @Column(nullable = false, name = "subtotal_quotation")
    private Integer subtotal;

    @Column(nullable = false, name = "descuento_quotation")
    private Integer descuento;

    @Column(nullable = false, name = "total_quotation")
    private Double total;

    @Column(nullable = false, name = "estado_quotation")
    private String estado;

    @Column(nullable = false, name = "fecha_emision_quotation")
    private LocalDate fechaEmision;

    @Column(nullable = false, name = "fecha_vencimiento_quotation")
    private LocalDate fechaVencimiento;
}
