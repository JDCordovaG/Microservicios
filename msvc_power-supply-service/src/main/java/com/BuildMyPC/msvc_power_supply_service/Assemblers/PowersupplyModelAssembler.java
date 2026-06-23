package com.BuildMyPC.msvc_power_supply_service.Assemblers;

import com.BuildMyPC.msvc_power_supply_service.Controllers.PowersupplyControllerV2;
import com.BuildMyPC.msvc_power_supply_service.Models.Dtos.PowersupplyDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PowersupplyModelAssembler implements RepresentationModelAssembler<PowersupplyDTO, EntityModel<PowersupplyDTO>> {

    @Override
    public EntityModel<PowersupplyDTO> toModel(PowersupplyDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PowersupplyControllerV2.class).buscarPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(PowersupplyControllerV2.class).listar()).withRel("fuentes-poder"));
    }
}