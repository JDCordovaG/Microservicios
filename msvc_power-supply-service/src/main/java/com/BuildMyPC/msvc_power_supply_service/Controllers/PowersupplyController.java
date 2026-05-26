package com.BuildMyPC.msvc_power_supply_service.Controllers;

import com.BuildMyPC.msvc_power_supply_service.Models.Dtos.PowersupplyDTO;
import com.BuildMyPC.msvc_power_supply_service.Models.Powersupply;
import com.BuildMyPC.msvc_power_supply_service.Services.PowersupplyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fuentes_poder")
public class PowersupplyController {

    private final PowersupplyService service;

    public PowersupplyController(PowersupplyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Powersupply> crear(@Valid @RequestBody PowersupplyDTO dto) {
        return new ResponseEntity<>(service.crearFuentePoder(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Powersupply>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Powersupply> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Powersupply> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarFuentePoder(id));
    }
}