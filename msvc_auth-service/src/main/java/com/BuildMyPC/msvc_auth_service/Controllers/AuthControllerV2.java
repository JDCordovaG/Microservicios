package com.BuildMyPC.msvc_auth_service.Controllers;

import com.BuildMyPC.msvc_auth_service.Assemblers.AuthModelAssembler;
import com.BuildMyPC.msvc_auth_service.Models.Auth;
import com.BuildMyPC.msvc_auth_service.Models.Dtos.AuthDTO;
import com.BuildMyPC.msvc_auth_service.Services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/auths")
@Validated
@Tag(name = "Auths V2", description = "Metodos CRUD HATEOAS para la gestión de auths")
public class AuthControllerV2 {

    private final AuthService service;

    // El assembler arma los enlaces HATEOAS de cada auth (ver AuthModelAssembler).
    @Autowired
    private AuthModelAssembler authModelAssembler;

    public AuthControllerV2(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Auth>> crear(@Valid @RequestBody AuthDTO dto) {
        Auth authcrear = this.service.crearAuth(dto);
        EntityModel<Auth> entityModel = this.authModelAssembler.toModel(authcrear);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityModel);
    }

    @GetMapping
    public ResponseEntity<List<Auth>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auth> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Auth> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarAuth(id));
    }
}
