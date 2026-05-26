package com.BuildMyPC.msvc_build_service.Controllers;

import com.BuildMyPC.msvc_build_service.Models.Build;
import com.BuildMyPC.msvc_build_service.Models.Dtos.BuildDTO;
import com.BuildMyPC.msvc_build_service.Services.BuildService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/builds")

public class BuildController {

    private final BuildService service;

    public BuildController(BuildService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Build> crear(@Valid @RequestBody BuildDTO dto) {
        return new ResponseEntity<>(service.crearBuild(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Build>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Build> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Build> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarBuild(id));
    }
}
