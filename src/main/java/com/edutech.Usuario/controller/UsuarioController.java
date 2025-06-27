package com.edutech.Usuario.controller;

import com.edutech.Usuario.model.Usuario;
import com.edutech.Usuario.service.UsuarioService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    public final UsuarioService userserv;
    public UsuarioController(UsuarioService userserv) {
        this.userserv = userserv;
    }

    @PostMapping
    public ResponseEntity<Usuario>
    agregarUsuario(@RequestBody Usuario usu) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userserv.guardar(usu));
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return userserv.mostrarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioId(@PathVariable int id){
        return userserv.obtenerUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id,
             @RequestBody Usuario data){
        Usuario u = userserv.obtenerUsuarioPorId(id);

        u.setNombre_usuario(data.getNombre_usuario());
        u.setApellido_usuario(data.getApellido_usuario());
        u.setCorreo_usuario(data.getCorreo_usuario());
        u.setTelefono_usuario(data.getTelefono_usuario());

        return userserv.guardar(u);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuarioxd(@PathVariable int id){
        userserv.eliminarUsuario(id);
    }

}
