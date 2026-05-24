package com.grupo_4.MSVC_fuente_poder.controllers;
import com.grupo_4.MSVC_fuente_poder.models.fuente_poderModel;
import com.grupo_4.MSVC_fuente_poder.dtos.fuente_poderDTO;
import com.grupo_4.MSVC_fuente_poder.services.fuente_poderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fuentes_poder")
public class fuente_poderController {

    private final fuente_poderService service;

    public fuente_poderController(fuente_poderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<fuente_poderModel> crear(@Valid @RequestBody fuente_poderDTO dto) {
        return new ResponseEntity<>(service.crearFuentePoder(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<fuente_poderModel>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<fuente_poderModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<fuente_poderModel> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarFuentePoder(id));
    }
}
