package com.BuildMyPC.msvc_auth_service.Models.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AuthDTO {

    @NotBlank(message = "El email del Auth es obligatorio")
    @Email(message = "El campo de email tiene que tener el formato de un email")
    private String email;

    @NotBlank(message = "El campo passwordHash no puede estar vacio")
    private String passwordHash;

    @NotBlank(message = "El campo rol no puede estar vacio")
    private String rol;

    @NotBlank(message = "El campo estado no puede estar vacio")
    private String estado;

    @NotNull(message = "El campo fechaCreacion es obligatoria")
    private LocalDate fechaCreacion;

    @NotNull(message = "El campo ultimoLogin es obligatoria")
    @DateTimeFormat
    private LocalDate ultimoLogin;

}