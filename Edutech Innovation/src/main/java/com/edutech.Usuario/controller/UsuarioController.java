package com.edutech.Usuario.controller;

import com.edutech.Assemblers.UsuarioAssembler;
import com.edutech.Usuario.model.Usuario;
import com.edutech.Usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Controller de usuarios", description = "Manejo de usuarios")

public class UsuarioController {
    public final UsuarioService userserv;
    public final UsuarioAssembler usuarioAssembler;

    public UsuarioController(UsuarioService userserv, UsuarioAssembler usuarioAssembler) {
        this.userserv = userserv;
        this.usuarioAssembler = usuarioAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> agregarUsuario(@RequestBody Usuario usu) {
        Usuario nuevoUsuario = userserv.guardar(usu);
        EntityModel<Usuario> usuarioConEnlaces = usuarioAssembler.toModel(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioConEnlaces);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> buscarUsuarioId(@PathVariable int id){
        Usuario usuario = userserv.obtenerUsuarioPorId(id);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        EntityModel<Usuario> usuarioWithLink = usuarioAssembler.toModel(usuario);
        return ResponseEntity.ok(usuarioWithLink);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listarUsuarios(){
        List<Usuario> usu = userserv.mostrarUsuarios();
        CollectionModel<EntityModel<Usuario>> usuarioWithLink = usuarioAssembler.toCollectionModel(usu);
        return ResponseEntity.ok(usuarioWithLink);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> actualizarUsuario(@PathVariable int id, @RequestBody Usuario data){
        Usuario u = userserv.obtenerUsuarioPorId(id);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }

        u.setNombre_usuario(data.getNombre_usuario());
        u.setApellido_usuario(data.getApellido_usuario());
        u.setCorreo_usuario(data.getCorreo_usuario());
        u.setTelefono_usuario(data.getTelefono_usuario());

        Usuario usuarioActualizado = userserv.guardar(u);
        EntityModel<Usuario> usuarioConEnlaces = usuarioAssembler.toModel(usuarioActualizado);
        return ResponseEntity.ok(usuarioConEnlaces);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<Void> eliminarUsuario(@PathVariable int id){
       userserv.eliminarUsuario(id);
       return ResponseEntity.noContent().build();
   }

}
