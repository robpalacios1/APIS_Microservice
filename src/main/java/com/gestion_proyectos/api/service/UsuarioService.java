package com.gestion_proyectos.api.service;

import com.gestion_proyectos.api.dto.CrearUsuarioDataTransferObject;
import com.gestion_proyectos.api.entity.Rol;
import com.gestion_proyectos.api.entity.Usuario;
import com.gestion_proyectos.api.repository.RolRepository;
import com.gestion_proyectos.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;

// Logic  for users
@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private RolRepository rolRepository;

  public Usuario crearUsuario(CrearUsuarioDataTransferObject usuarioDataTransferObject) {
    // Search Rol in BD by name
    Rol rol = rolRepository.findByNombre(usuarioDataTransferObject.getRol())
        .orElseThrow(() -> new RuntimeException("Error:Rol no encontrado"));

    // Create Usuario entity and set its properties
    Usuario usuario = new Usuario();
    usuario.setNombre(usuarioDataTransferObject.getNombre());
    usuario.setEmail(usuarioDataTransferObject.getEmail());
    Set<Rol> roles = new HashSet<>();
    roles.add(rol);
    usuario.setRoles(roles); // Assign the found Rol to the Usuario

    // Save Usuario entity to the database
    return usuarioRepository.save(usuario);
  }
}
