package com.BuildMyPC.msvc_auth_service.Models.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthDTO {

    @NotBlank(message = "El email del Auth es obligatorio")
    @Email(message = "El campo de email tiene que tener el formato de un email")
    private String email;

    @NotBlank(message = "El campo passwordHash no puede estar vacio")
    private String passwordHash;

    @NotBlank(message = "El campo rol no puede estar vacio")
    private String rol;
}