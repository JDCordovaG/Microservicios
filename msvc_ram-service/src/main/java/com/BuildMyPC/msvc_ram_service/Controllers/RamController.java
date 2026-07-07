package com.BuildMyPC.msvc_ram_service.Controllers;

import com.BuildMyPC.msvc_ram_service.Models.Dtos.RamDTO;
import com.BuildMyPC.msvc_ram_service.Models.Ram;
import com.BuildMyPC.msvc_ram_service.Services.RamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rams")

public class RamController {

    private final RamService service;

    public RamController(RamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ram> crear(@Valid @RequestBody RamDTO dto) {
        return new ResponseEntity<>(service.crearRam(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ram>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ram> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

}
