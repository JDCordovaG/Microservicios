package com.BuildMyPC.msvc_power_supply_service.Controllers;

import com.BuildMyPC.msvc_power_supply_service.Assemblers.PowersupplyModelAssembler;
import com.BuildMyPC.msvc_power_supply_service.Models.Dtos.PowersupplyDTO;
import com.BuildMyPC.msvc_power_supply_service.Services.PowersupplyService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/fuentes-poder")
public class PowersupplyControllerV2 {

    private final PowersupplyService service;
    private final PowersupplyModelAssembler assembler;

    public PowersupplyControllerV2(PowersupplyService service, PowersupplyModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<PowersupplyDTO>> listar() {
        List<EntityModel<PowersupplyDTO>> fuentes = service.listarTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(fuentes, linkTo(methodOn(PowersupplyControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PowersupplyDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(service.buscarPorId(id)));
    }
}