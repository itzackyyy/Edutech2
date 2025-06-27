package com.edutech.Soporte.controller;


import com.edutech.Soporte.model.SoporteCliente;
import com.edutech.Soporte.service.SoporteService;
import com.edutech.Usuario.model.Usuario;
import com.edutech.Usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
public class SoporteController {

    private final SoporteService soportadoxd;

    public SoporteController(SoporteService soportadoxd) {
        this.soportadoxd = soportadoxd;
    }

    //Funciona
    @PostMapping
    public ResponseEntity<SoporteCliente> crearSoporte(@RequestBody SoporteCliente suporsito) {
        return ResponseEntity.status(HttpStatus.CREATED).body(soportadoxd.guardarSoporte(suporsito));
    }

    /**
     * llamar a un endpoint desde un controller hacia otro :D
     */

    //Funciona
    @Autowired
    private UsuarioService usuarioService;

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuarioDesdeSoporte(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }


    //Funciona
    @PutMapping("/usuarios/{id}")
    public Usuario actualizarUsuarioDesdeSoporte(@PathVariable Integer id, @RequestBody Usuario mamut) {
        Usuario usi = usuarioService.obtenerUsuarioPorId(id);

        usi.setNombre_usuario(mamut.getNombre_usuario());
        usi.setApellido_usuario(mamut.getApellido_usuario());
        usi.setCorreo_usuario(mamut.getCorreo_usuario());
        usi.setTelefono_usuario(mamut.getTelefono_usuario());

        return usuarioService.guardar(usi);


    }

    //Funciona
    @GetMapping("/usuarios/{id}")
    public Usuario encontrarUsuarioPorId(@PathVariable Integer id) {
        usuarioService.obtenerUsuarioPorId(id);
        return usuarioService.obtenerUsuarioPorId(id);
    }

    /**
     *
     * No funciona
     *
    @GetMapping("/{id}")
    public SoporteCliente encontrarSoportePorId(@PathVariable Integer id) {
        soportadoxd.buscarSoportePorId(id);
        return soportadoxd.buscarSoportePorId(id);
    }

     **/

    @GetMapping
    public List<SoporteCliente> mostrarSoporte() {
        return soportadoxd.listarSoporte();
    }


    //Eliminar usuarios de Soporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSoporte(@PathVariable Integer id) {
        soportadoxd.eliminarSoporte(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteCliente> actualizarPerfilSoporte(@PathVariable Integer id, @RequestBody SoporteCliente supy) {
        SoporteCliente s = soportadoxd.buscarSoportePorId(id);

        s.setNombre_soporte(supy.getNombre_soporte());
        s.setApellido_soporte(supy.getApellido_soporte());
        s.setCorreo_soporte(supy.getCorreo_soporte());
        s.setNumero_soporte(supy.getNumero_soporte());

        SoporteCliente guardateaaa = soportadoxd.guardarSoporte(s);

        return ResponseEntity.noContent().build();
    }
}//fin clase
