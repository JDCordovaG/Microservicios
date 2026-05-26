package com.BuildMyPC.msvc_auth_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rols")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(nullable = false, name = "nombre_rol")
    private Long nombre;

    @Column(nullable = false, name = "descripcion_rol")
    private String descripcion;

}
