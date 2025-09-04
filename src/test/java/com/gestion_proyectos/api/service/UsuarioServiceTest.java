package com.gestion_proyectos.api.service;

import com.gestion_proyectos.api.repository.UsuarioRepository;
import com.gestion_proyectos.api.repository.RolRepository;
import com.gestion_proyectos.api.entity.Rol;
import com.gestion_proyectos.api.entity.Usuario;
import com.gestion_proyectos.api.dto.CrearUsuarioDataTransferObject;
import java.util.Set;
import java.util.Optional;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  @Mock
  private RolRepository rolRepository;

  @InjectMocks
  private UsuarioService usuarioService;

  @Test
  void testcrearUsuarioExitoso() {
    CrearUsuarioDataTransferObject usuarioDataTransferObject = new CrearUsuarioDataTransferObject();
    usuarioDataTransferObject.setNombre("Usuario de prueba");
    usuarioDataTransferObject.setEmail("prueba@example.com");
    usuarioDataTransferObject.setRol("DEV");

    Rol rolDev = new Rol();
    rolDev.setNombre("DEV");

    Usuario usuarioGuardado = new Usuario();
    usuarioGuardado.setId(1L);
    usuarioGuardado.setNombre("Usuario de prueba");
    usuarioGuardado.setRoles(Set.of(rolDev));

    when(rolRepository.findByNombre("DEV")).thenReturn(Optional.of(rolDev));
    when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

    Usuario resultado = usuarioService.crearUsuario(usuarioDataTransferObject);

    assertNotNull(resultado);
    assertEquals("Usuario de prueba", resultado.getNombre());
    assertEquals(1, resultado.getRoles().size());
    verify(usuarioRepository, times(1)).save(any(Usuario.class));
  }
}
