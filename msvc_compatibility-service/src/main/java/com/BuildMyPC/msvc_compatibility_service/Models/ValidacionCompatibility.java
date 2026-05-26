package com.BuildMyPC.msvc_compatibility_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "validacioncompatibilities")
public class ValidacionCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valicompatibility_id")
    private Long id;

    @Column(nullable = false, name = "build_id")
    private Long buildId;

    @Column(nullable = false, name = "compatible_valicompatibility")
    private String compatible;

    @Column(nullable = false, name = "consumo_estimado_watts_valivalicompatibility")
    private Integer consumoEstimadoWatts;

    @Column(nullable = false, name = "margen_fuente_valicompatibility")
    private String margenFuente;

    @Column(nullable = false, name = "observaciones_valicompatibility")
    private String observaciones;

    @Column(nullable = false, name = "fecha_validacion_valicompatibility")
    private LocalDate fechaValidacion;

}
