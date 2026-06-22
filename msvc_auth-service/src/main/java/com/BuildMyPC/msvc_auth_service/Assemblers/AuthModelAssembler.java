package com.BuildMyPC.msvc_auth_service.Assemblers;

import com.BuildMyPC.msvc_auth_service.Controllers.AuthControllerV2;
import com.BuildMyPC.msvc_auth_service.Models.Auth;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Ensamblador HATEOAS para la entidad {@link Auth}.
 *
 * <p>Transforma una {@code Auth} en un {@link EntityModel} agregando los
 * enlaces de navegación (self y colección) expuestos por la API v2.</p>
 */
// @Component: registra esta clase como bean para poder inyectarla en el controlador V2.
// RepresentationModelAssembler<Auth, EntityModel<Auth>>: convierte una Auth (entidad)
// en un EntityModel (la entidad + enlaces HATEOAS), todo en un solo lugar reutilizable.
@Component
public class AuthModelAssembler implements RepresentationModelAssembler<Auth, EntityModel<Auth>> {

    /**
     * Construye la representación HATEOAS de una auth.
     *
     * @param auth entidad de dominio a representar
     * @return modelo con la auth y los enlaces {@code self} y {@code auths}
     */
    @Override
    public EntityModel<Auth> toModel(Auth auth) {
        // EntityModel.of(datos, ...enlaces) empaqueta la auth junto a sus links.
        return EntityModel.of(
                auth,
                // self: enlace al propio recurso (GET /api/v2/auths/{id}). linkTo + methodOn
                // arman la URL leyendo el mapeo del metodo, sin escribirla a mano.
                linkTo(methodOn(AuthControllerV2.class).buscarPorId(auth.getId())).withSelfRel(),
                // auths: enlace al listado completo, para que el cliente sepa como navegar.
                linkTo(methodOn(AuthControllerV2.class).listar()).withRel("auths"));
    }
}
