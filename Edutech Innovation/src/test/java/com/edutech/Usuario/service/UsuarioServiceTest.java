package com.edutech.Usuario.service;

import com.edutech.Usuario.model.Usuario;
import com.edutech.Usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {


    @Mock
    private UsuarioRepository repouser;



    @InjectMocks
    private UsuarioService usuarioService;
    private Usuario u,u_2;
    private List<Usuario> mostrarUsuarios;

    @BeforeEach
    void setUp() {

        u = new Usuario(1,"Juan", "Perez", "juan.perez@example.com", "123456789");
        u_2 = new Usuario(2,"Ana", "Jara", "an.jra@example.com", "987654321");

        mostrarUsuarios = Arrays.asList(u,u_2);
    }

    @Test
    void testGuardarUsuario() {
        when(repouser.save(any(Usuario.class))).thenReturn(u);
        Usuario usuarioGuardado = usuarioService.guardar(u);
        verify(repouser, times(1)).save(u);

        assertNotNull(usuarioGuardado);
        assertEquals(u.getNombre_usuario(), usuarioGuardado.getNombre_usuario());
        assertEquals(u.getId_usuario(), usuarioGuardado.getId_usuario());
    }

    @Test
    void testObtenerUsuarioPorIdExistente() {
        when(repouser.findById(1)).thenReturn(Optional.of(u)); //cambiar a u para test god
        Usuario usuarioEncontrado = usuarioService.obtenerUsuarioPorId(1);
        System.out.println("id del usuario: "+usuarioEncontrado.getApellido_usuario());//para revisar si esta devolviendo el que se definio mas arriba

        verify(repouser, times(1)).findById(1);
        assertNotNull(usuarioEncontrado);
        assertEquals(1, usuarioEncontrado.getId_usuario());
        assertEquals("Juan", usuarioEncontrado.getNombre_usuario());
    }

    @Test
    void testObtenerUsuarioPorIdNoExistente() {

        int nonExistentId = 99;
        when(repouser.findById(nonExistentId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.obtenerUsuarioPorId(nonExistentId);
        });

            String expectedMessage = "El usuario con la id: " + nonExistentId + " no se ha encontrado o no existe";
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
            verify(repouser, times(1)).findById(nonExistentId); //aca se comprueba realmente si existe la id 99
        }


    @Test
    void testEliminarUsuario() {
        doNothing().when(repouser).deleteById(1);
        usuarioService.eliminarUsuario(1);
        verify(repouser, times(1)).deleteById(1);

    }

    @Test
    void testEliminarUsuario2(){
        doNothing().when(repouser).deleteById(1);
        repouser.deleteById(1);
        verify(repouser, times(1)).deleteById(1);
    }

    @Test
    void listarUsuarios(){
        when(repouser.findAll()).thenReturn(mostrarUsuarios);
        List<Usuario> usuariosObtenidos = usuarioService.mostrarUsuarios();

        verify(repouser, times(1)).findAll();
        assertNotNull(usuariosObtenidos);
        assertEquals(2,usuariosObtenidos.size());
        assertEquals(mostrarUsuarios.getFirst().getNombre_usuario(), usuariosObtenidos.getFirst().getNombre_usuario());
        assertEquals(mostrarUsuarios, usuariosObtenidos);
        System.out.println(usuariosObtenidos.toString()); //para ver que es lo que esta entregando
    }
}