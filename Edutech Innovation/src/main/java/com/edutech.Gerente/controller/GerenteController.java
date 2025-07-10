package com.edutech.Gerente.controller;


import com.edutech.Gerente.model.Gerente;
import com.edutech.Gerente.service.GerenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gerente")
public class GerenteController {//inicio cla
    private final GerenteService maid;
    public GerenteController(GerenteService maid) {
        this.maid = maid;
    }

    //agregar
    @PostMapping
    public ResponseEntity<Gerente> crearGerenteMaid(@RequestBody Gerente ger) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maid.guardarGerente(ger));
    }

    @GetMapping
    public List<Gerente> obtenerGer(){
        return maid.listarGerentes();
    }


    @GetMapping("/{id}")
    public Gerente obtenerGerentePorId(@PathVariable Integer id) {
        return maid.buscarGerente(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> actualizarGerente(@PathVariable Integer id, @RequestBody Gerente gueas) {
        Gerente ges = maid.buscarGerente(id);

        ges.setNombre_gerente(gueas.getNombre_gerente());
        ges.setApellido_gerente(gueas.getApellido_gerente());

        Gerente guardateaaa = maid.guardarGerente(ges);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarGerente(@PathVariable Integer id) {
        maid.eliminarGerente(id);
    }






}//fin cla
