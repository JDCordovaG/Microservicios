package com.BuildMyPC.msvc_auth_service.Assemblers;

import com.BuildMyPC.msvc_auth_service.Controllers.AuthControllerV2;
import com.BuildMyPC.msvc_auth_service.Models.Auth;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AuthModelAssembler implements RepresentationModelAssembler<Auth, EntityModel<Auth>> {

    @Override
    public EntityModel<Auth> toModel(Auth auth) {
        return EntityModel.of(
                auth,
                linkTo(methodOn(AuthControllerV2.class).buscarPorId(auth.getId())).withSelfRel(),
                linkTo(methodOn(AuthControllerV2.class).listar()).withRel("auths"));
    }
}
