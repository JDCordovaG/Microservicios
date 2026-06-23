package com.BuildMyPC.msvc_gpu_service.Controllers;

import com.BuildMyPC.msvc_gpu_service.Assemblers.GpuModelAssembler;
import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import com.BuildMyPC.msvc_gpu_service.Services.GpuService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/gpus")
public class GpuControllerV2 {

    private final GpuService service;
    private final GpuModelAssembler assembler;

    public GpuControllerV2(GpuService service, GpuModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<GpuDTO>> listar() {
        List<EntityModel<GpuDTO>> gpus = service.listarTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(gpus, linkTo(methodOn(GpuControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<GpuDTO>> buscarPorId(@PathVariable Long id) {
        GpuDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(dto));
    }
}