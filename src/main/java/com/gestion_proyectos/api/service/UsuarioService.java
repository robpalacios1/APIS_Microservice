package com.gestion_proyectos.api.service;

import com.gestion_proyectos.api.dto.CrearUsuarioDataTransferObject;
import com.gestion_proyectos.api.entity.Rol;
import com.gestion_proyectos.api.entity.Usuario;
import com.gestion_proyectos.api.repository.RolRepository;
import com.gestion_proyectos.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private RolRepository RolRepository;

  public Usuario crearUsuario(CrearUsuarioDataTransferObject usuarioDataTransferObject) {
    // Search Rol in BD by name
    Rol rol = RolRepository.findByNombre(usuarioDataTransferObject.getRol())
        .orElseThrow(() -> new RuntimeException("Error:Rol no encontrado"));

    // Create Usuario entity and set its properties
    Usuario usuario = new Usuario();
    usuario.setNombre(usuarioDataTransferObject.getNombre());
    usuario.setEmail(usuarioDataTransferObject.getEmail());
    usuario.setRol(usuarioDataTransferObject.getRol()); // Assign the found role to the user

    // Save Usuario entity to the database
    return usuarioRepository.save(usuario);
  }
}
