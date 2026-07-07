package com.BuildMyPC.msvc_user_service.Controllers;

import com.BuildMyPC.msvc_user_service.Models.Dtos.UserDTO;
import com.BuildMyPC.msvc_user_service.Models.User;
import com.BuildMyPC.msvc_user_service.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> crear(@Valid @RequestBody UserDTO dto) {
        return new ResponseEntity<>(service.crearUser(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<User> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarUser(id));
    }
}
