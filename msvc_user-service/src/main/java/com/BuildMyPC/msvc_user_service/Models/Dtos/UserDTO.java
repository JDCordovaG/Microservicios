package com.BuildMyPC.msvc_user_service.Models.Dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    @NotNull(message = "El nombre del user es obligatorio")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacio")
    private String apellido;

    @NotNull(message = "El campo email no puede estar vacio")
    @Email
    private String email;

    @NotNull(message = "El campo telefono no puede estar vacio")
    private String telefono;

    @NotNull(message = "El campo rol Funcional es obligatoria")
    private String rolFuncional;

    @NotNull(message = "El campo estado es obligatoria")
    private String estado;

    @NotNull(message = "El campo fecha de Registro no puede estar vacio")
    private LocalDate fechaRegistro;

}
