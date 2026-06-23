package com.BuildMyPC.msvc_motherboard_service.Controllers;

import com.BuildMyPC.msvc_motherboard_service.Assemblers.MotherboardModelAssembler;
import com.BuildMyPC.msvc_motherboard_service.Models.Dtos.MotherboardDTO;
import com.BuildMyPC.msvc_motherboard_service.Services.MotherboardService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/placas-madre")
public class MotherboardControllerV2 {

    private final MotherboardService service;
    private final MotherboardModelAssembler assembler;

    public MotherboardControllerV2(MotherboardService service, MotherboardModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<MotherboardDTO>> listar() {
        List<EntityModel<MotherboardDTO>> boards = service.listarTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(boards, linkTo(methodOn(MotherboardControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<MotherboardDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(service.buscarPorId(id)));
    }
}