package com.BuildMyPC.msvc_quotation_service.Controllers;

import com.BuildMyPC.msvc_quotation_service.Models.Dtos.QuotationDTO;
import com.BuildMyPC.msvc_quotation_service.Models.Quotation;
import com.BuildMyPC.msvc_quotation_service.Services.QuotationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotations")

public class QuotationController {

    private final QuotationService service;

    public QuotationController(QuotationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Quotation> crear(@Valid @RequestBody QuotationDTO dto) {
        return new ResponseEntity<>(service.crearQuotation(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Quotation>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quotation> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Quotation> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivarQuotation(id));
    }
}

