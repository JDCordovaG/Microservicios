package com.BuildMyPC.msvc_auth_service.Controllers;

import com.BuildMyPC.msvc_auth_service.Models.Auth;
import com.BuildMyPC.msvc_auth_service.Models.Dtos.AuthDTO;
import com.BuildMyPC.msvc_auth_service.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auths")

public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Auth> crear(@Valid @RequestBody AuthDTO dto) {
        return new ResponseEntity<>(service.crearAuth(dto), HttpStatus.CREATED);
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
