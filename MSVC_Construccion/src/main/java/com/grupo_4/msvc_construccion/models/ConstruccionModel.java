package com.grupo_4.msvc_construccion.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "construcciones")

public class ConstruccionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "build_id")
    private Long id;

    @Column(nullable = false, name = "usuario_id")
    private Long usuarioId; // Por ahora simularemos este ID en Postman

    @Column(nullable = false, name = "cpu_id")
    private Long cpuId;

    @Column(nullable = false, name = "gpu_id")
    private Long gpuId;

    @Column(nullable = false, name = "motherboard_id")
    private Long motherboardId;

    @Column(nullable = false, name = "ram_id")
    private Long ramId;

    @Column(nullable = false, name = "fuente_id")
    private Long fuenteId;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

}
