package com.edutech.Usuario.service;

import com.edutech.Usuario.model.Usuario;
import com.edutech.Usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {//INICIO CLASE
    private final UsuarioRepository repouser;
    public UsuarioService(UsuarioRepository repouser) {
        this.repouser = repouser;
    }


    //guardar usuario
    public Usuario guardar(Usuario u){
        return repouser.save(u);
    }

    //lista que contendra usuarios y que permitira mostrarlos
    public List<Usuario> mostrarUsuarios(){
        return repouser.findAll();
    }

    // obtener usuario por su id

    public Usuario obtenerUsuarioPorId(Integer id){
        return repouser.findById(id).orElseThrow(() -> new EntityNotFoundException ("El usuario con la id: "+id+" no se ha encontrado o no existe"));
    }

    //eliminar usuario por su id
    public void eliminarUsuario(Integer id){
        repouser.deleteById(id);
    }



}//FIN CLASE

