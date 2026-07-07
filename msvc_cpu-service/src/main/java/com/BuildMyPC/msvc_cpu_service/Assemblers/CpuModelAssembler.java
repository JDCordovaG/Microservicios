package com.BuildMyPC.msvc_cpu_service.Assemblers;

import com.BuildMyPC.msvc_cpu_service.Controllers.CpuControllerV2;
import com.BuildMyPC.msvc_cpu_service.Models.Dtos.CpuDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CpuModelAssembler implements RepresentationModelAssembler<CpuDTO, EntityModel<CpuDTO>> {

    @Override
    public EntityModel<CpuDTO> toModel(CpuDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(CpuControllerV2.class).obtenerPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(CpuControllerV2.class).obtenerTodos()).withRel("cpus"));
    }
}