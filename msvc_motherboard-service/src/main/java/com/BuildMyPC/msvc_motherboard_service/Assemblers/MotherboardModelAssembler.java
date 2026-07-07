package com.BuildMyPC.msvc_motherboard_service.Assemblers;

import com.BuildMyPC.msvc_motherboard_service.Controllers.MotherboardControllerV2;
import com.BuildMyPC.msvc_motherboard_service.Models.Dtos.MotherboardDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MotherboardModelAssembler implements RepresentationModelAssembler<MotherboardDTO, EntityModel<MotherboardDTO>> {

    @Override
    public EntityModel<MotherboardDTO> toModel(MotherboardDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(MotherboardControllerV2.class).buscarPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(MotherboardControllerV2.class).listar()).withRel("placas_madre"));
    }
}