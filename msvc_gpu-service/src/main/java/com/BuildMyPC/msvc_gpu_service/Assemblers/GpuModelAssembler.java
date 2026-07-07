package com.BuildMyPC.msvc_gpu_service.Assemblers;

import com.BuildMyPC.msvc_gpu_service.Controllers.GpuControllerV2;
import com.BuildMyPC.msvc_gpu_service.Models.Dtos.GpuDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GpuModelAssembler implements RepresentationModelAssembler<GpuDTO, EntityModel<GpuDTO>> {

    @Override
    public EntityModel<GpuDTO> toModel(GpuDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(GpuControllerV2.class).buscarPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(GpuControllerV2.class).listar()).withRel("gpus"));
    }
}