package com.BuildMyPC.msvc_build_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "builds")
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "build_id")
    private Long id;

    @Column(nullable = false, name = "usuario_id")
    private Long usuarioId;

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

    @Column(nullable = false, name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Embedded
    private Audit audit = new Audit();
}