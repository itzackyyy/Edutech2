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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;


    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setUp() {

        usuario1 = new Usuario(1, "Juan", "Perez", "juan.perez@example.com", "123456789");
        usuario2 = new Usuario(2, "Ana", "Gonzales", "ana.gonz@example.com", "987654321");
    }

    @Test
    void testGuardarUsuario() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarioList = usuarioService.mostrarUsuarios();


        verify(usuarioRepository, times(1)).findAll();
        assertNotNull(usuarioList);
        assertEquals(2, usuarioList.size());
        assertEquals("Juan", usuarioList.get(0).getNombre_usuario());
    }

    @Test
    void testObtenerUsuarioPorIdExistente() {
        // Cuando se llama a findById con el ID 1, debe retornar un Optional con usuario1
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario1));

        Usuario usuarioEncontrado = usuarioService.obtenerUsuarioPorId(1);

        // Verificamos que findById fue llamado una vez con el ID 1
        verify(usuarioRepository, times(1)).findById(1);
        // Verificamos que el usuario encontrado es el esperado
        assertNotNull(usuarioEncontrado);
        assertEquals(1, usuarioEncontrado.getId_usuario());
        assertEquals("Juan", usuarioEncontrado.getNombre_usuario());
    }

    @Test
    void testObtenerUsuarioPorIdNoExistente() {
        // Cuando se llama a findById con un ID no existente, debe retornar un Optional vacío
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        // Verificamos que se lanza la excepción EntityNotFoundException
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.obtenerUsuarioPorId(99);
        });

        // Verificamos el mensaje de la excepción
        String expectedMessage = "El usuario con la id: 99 no se ha encontrado o no existe";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        // Verificamos que findById fue llamado una vez con el ID 99
        verify(usuarioRepository, times(1)).findById(99);
    }

    @Test
    void testEliminarUsuario() {
        // No necesitamos que deleteById retorne nada, solo verificamos que fue llamado
        doNothing().when(usuarioRepository).deleteById(1);

        usuarioService.eliminarUsuario(1);

        // Verificamos que deleteById fue llamado una vez con el ID 1
        verify(usuarioRepository, times(1)).deleteById(1);

    }
}