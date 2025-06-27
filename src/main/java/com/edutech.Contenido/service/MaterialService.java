package com.edutech.Contenido.service;

import com.edutech.Contenido.model.Material;
import com.edutech.Contenido.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Material guardarMaterial(Material mate) {
        return materialRepository.save(mate);
    }

    public List<Material> mostrarMaterial() {
        return materialRepository.findAll();
    }

    public Material obtenerMaterial(int id) {
        return materialRepository.findById(id).orElseThrow(()
        -> new RuntimeException("el material con el id: " + id+ " no existe"));
    }

    public void eliminarMaterial(Integer id) {
        materialRepository.deleteById(id);
    }

}
