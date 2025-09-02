package com.gestion_proyectos.api.controller;


import com.gestion_proyectos.api.dto.CrearUsuarioDataTransferObject;
import com.gestion_proyectos.api.entity.Usuario;
import com.gestion_proyectos.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Usuario> registrarUsuario(@RequestBody CrearUsuarioDataTransferObject usuarioDataTransferObject) {
    Usuario nuevoUsuario = usuarioService.crearUsuario(usuarioDataTransferObject);
    return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
  }
}
