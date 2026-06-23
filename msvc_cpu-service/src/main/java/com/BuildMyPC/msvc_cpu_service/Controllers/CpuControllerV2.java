package com.BuildMyPC.msvc_cpu_service.Controllers;

import com.BuildMyPC.msvc_cpu_service.Assemblers.CpuModelAssembler;
import com.BuildMyPC.msvc_cpu_service.Models.Dtos.CpuDTO;
import com.BuildMyPC.msvc_cpu_service.Services.CpuService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/cpus")
public class CpuControllerV2 {

    private final CpuService cpuService;
    private final CpuModelAssembler assembler;

    public CpuControllerV2(CpuService cpuService, CpuModelAssembler assembler) {
        this.cpuService = cpuService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<CpuDTO>> obtenerTodos() {
        List<EntityModel<CpuDTO>> cpus = cpuService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cpus, linkTo(methodOn(CpuControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CpuDTO>> obtenerPorId(@PathVariable Long id) {
        CpuDTO dto = cpuService.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(dto));
    }
}