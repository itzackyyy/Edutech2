package com.edutech.Assemblers;

import com.edutech.Usuario.controller.UsuarioController;
import com.edutech.Usuario.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario u) {
        return EntityModel.of(u,
                linkTo(methodOn(UsuarioController.class).buscarUsuarioId(u.getId_usuario())).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).listarUsuarios()).withRel("usuarios"),
                        linkTo(methodOn(UsuarioController.class).eliminarUsuario(u.getId_usuario())).withRel("eliminar"),
                            linkTo(methodOn(UsuarioController.class).actualizarUsuario(u.getId_usuario(),u)).withRel("Actualizar"));}
}
