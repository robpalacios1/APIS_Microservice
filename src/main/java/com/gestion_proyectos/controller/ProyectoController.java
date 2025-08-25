package com.gestion_proyectos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_proyectos.entity.Proyecto;
import com.gestion_proyectos.service.ProyectoService;
import com.gestion_proyectos.dto.CrearProyectoDataTransferObject;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

  @Autowired
  private ProyectoService proyectoService;

  @PostMapping
  public ResponseEntity<Proyecto> registrarProyecto(@RequestBody CrearProyectoDataTransferObject proyectoDTO) {
    Proyecto proyectoGuardado = proyectoService.crearProyecto(proyectoDTO);
    // Return the created project with HTTP status 201 (Created)
    return new ResponseEntity<>(proyectoGuardado, HttpStatus.CREATED);
  }
}
