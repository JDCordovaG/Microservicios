package com.BuildMyPC.msvc_compatibility_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "detacompatibilities")
public class DetalleCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detallecompatibility_id")
    private Long id;

    @Column(nullable = false, name = "validacion_id_detacompatibilities")
    private Long validacionId;

    @Column(nullable = false, name = "regla_detacompatibilities")
    private String regla;

    @Column(nullable = false, name = "resultado_detacompatibilities")
    private String resultado;

    @Column(nullable = false, name = "mensaje_detacompatibilities")
    private String mensaje;

}
