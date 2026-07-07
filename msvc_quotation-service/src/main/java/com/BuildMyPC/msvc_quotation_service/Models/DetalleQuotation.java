package com.BuildMyPC.msvc_quotation_service.Models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DetalleQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalledetallequotation_id")
    private Long id;

    @Column(nullable = false, name = "detallequotation_id")
    private Long cotizacionId;

    @Column(nullable = false, name = "usuario_id_detallequotation")
    private String usuarioId;

    @Column(nullable = false, name = "subtotal_detallequotation")
    private Integer subtotal;

    @Column(nullable = false, name = "descuento_detallequotation")
    private Integer descuento;

    @Column(nullable = false, name = "total_detallequotation")
    private Double total;

    @Column(nullable = false, name = "estado_detallequotation")
    private Double estado;

    @Column(nullable = false, name = "fecha_emision_detallequotation")
    private Integer fechaEmision;

    @Column(nullable = false, name = "fecha_vencimiento_detallequotation")
    private Boolean fechaVencimiento;

}
