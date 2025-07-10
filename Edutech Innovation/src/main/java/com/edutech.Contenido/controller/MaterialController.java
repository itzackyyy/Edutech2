package com.edutech.Contenido.controller;


import com.edutech.Contenido.model.Material;
import com.edutech.Contenido.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/material")
public class MaterialController {//inicio clase
    private final MaterialService materialService;
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<Material>crearMaterial(@RequestBody Material ma) {

        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.guardarMaterial(ma));
    }

    @GetMapping
    public List<Material> listarMaterial() {
        return materialService.mostrarMaterial();
    }

    @GetMapping("/{id}")
    public Material obtenerMate(@PathVariable Integer id) {
        return materialService.obtenerMaterial(id);
    }

    @PutMapping("/{id}")
    public Material actualizarMaterial(@PathVariable Integer id, @RequestBody Material material) {
        Material k = materialService.obtenerMaterial(id);
        return materialService.guardarMaterial(k);
    }
}//final clase
