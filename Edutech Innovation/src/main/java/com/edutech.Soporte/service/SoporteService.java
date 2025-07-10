package com.edutech.Soporte.service;


import com.edutech.Soporte.model.SoporteCliente;
import com.edutech.Soporte.repository.ServicioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SoporteService {

    private final ServicioRepository servrepo;
    public SoporteService(ServicioRepository servrepo) {this.servrepo = servrepo;}

    public SoporteCliente guardarSoporte(SoporteCliente sop) {
        return servrepo.save(sop);
    }

    public List<SoporteCliente> listarSoporte() {
        return servrepo.findAll();
    }

    public SoporteCliente buscarSoportePorId(Integer id) {
        return servrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("el agente de soporte con la id: " +id+ " no existe"));
    }

    public void eliminarSoporte(Integer id) {
        servrepo.deleteById(id);
    }

}
