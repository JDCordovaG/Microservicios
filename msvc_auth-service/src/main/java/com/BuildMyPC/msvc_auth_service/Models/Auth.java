package com.BuildMyPC.msvc_auth_service.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "auths")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    @Column(nullable = false, name = "email_auth",unique = true)
    private String email;

    @Column(nullable = false, name = "password_hash_auth")
    private String passwordHash;

    @Column(nullable = false, name = "rol_auth")
    private String rol;

    @Column(nullable = false, name = "estado_auth")
    private String estado;

    @Column(nullable = false, name = "fecha_creacion_auth")
    private LocalDate fechaCreacion;

    @Column(nullable = false, name = "ultimo_login_auth")
    private LocalDate ultimoLogin;

}
