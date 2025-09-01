package com.gestion_proyectos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_proyectos.api.dto.AsignacionDataTransferObject;
import com.gestion_proyectos.api.dto.AsignarDesarrolladoresDataTransferObject;
import com.gestion_proyectos.api.dto.CrearProyectoDataTransferObject;
import com.gestion_proyectos.api.entity.Proyecto;
import com.gestion_proyectos.api.service.ProyectoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

  @GetMapping
  public List<Proyecto> obtenerProyectos() {
    return proyectoService.obtenerProyectos();
  }

  @PutMapping("/{id}/asignar-lider")
  public ResponseEntity<Proyecto> asignarLider(
                                  @PathVariable Long id,
                                  @RequestBody AsignacionDataTransferObject dto) {
    Optional<Proyecto> proyectoActualizado = proyectoService.asignarLider(id, dto.getUsuarioId());

    if (proyectoActualizado.isPresent()) {
      return ResponseEntity.ok(proyectoActualizado.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}/asignar-dev")
  public ResponseEntity<Proyecto> asignarDesarrolladores(
                                  @PathVariable Long id,
                                  @RequestBody AsignarDesarrolladoresDataTransferObject dto) {
    Optional<Proyecto> proyectoActualizado = proyectoService.asignarDesarrolladores(id,
        dto.getDesarrolladoresDataTransferObject());

    if (proyectoActualizado.isPresent()) {
      return ResponseEntity.ok(proyectoActualizado.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}