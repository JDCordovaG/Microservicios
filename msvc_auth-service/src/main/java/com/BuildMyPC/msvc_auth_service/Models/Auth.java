package com.BuildMyPC.msvc_auth_service.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auths")
@Getter
@Setter
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private String estado;

    @Embedded
    private Audit audit = new Audit(); // Reemplaza los campos de fecha manuales
}
