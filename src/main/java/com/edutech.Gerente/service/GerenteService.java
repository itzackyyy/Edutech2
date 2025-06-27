package com.edutech.Gerente.service;
import com.edutech.Gerente.model.Gerente;
import com.edutech.Gerente.repository.GerenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteService {//inicio clase
    private final GerenteRepository repon;
    public GerenteService(GerenteRepository repon) {
        this.repon = repon;
    }

    public Gerente guardarGerente(Gerente g){
        return repon.save(g);
    }

    public List<Gerente> listarGerentes() {
        return repon.findAll();
    }

    public Gerente buscarGerente(Integer id) {
        return repon.findById(id)
                .orElseThrow(()->
                        new RuntimeException
                                ("el gerente con la id: " + id + " no existe"));
    }

    public void eliminarGerente(Integer id)
    {repon.deleteById(id);}
}//inicio clase
