package com.BuildMyPC.msvc_compatibility_service.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "detalle_compatibilidades")
public class DetalleCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validacion_id", nullable = false)
    @JsonBackReference
    private ValidacionCompatibility validacion;

    @Column(nullable = false)
    private String regla;

    @Column(nullable = false)
    private String resultado;

    @Column(nullable = false)
    private String mensaje;
}
