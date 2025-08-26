package com.gestion_proyectos.api.service;

import com.gestion_proyectos.api.dto.CrearProyectoDataTransferObject;
import com.gestion_proyectos.api.entity.Proyecto;
import com.gestion_proyectos.api.repository.ProyectoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProyectoService {

  @Autowired
  private ProyectoRepository proyectoRepository;

  public Proyecto crearProyecto(CrearProyectoDataTransferObject crearProyectoDataTransferObject) {
    //
    Proyecto nuevoProyecto = new Proyecto();

    //
    nuevoProyecto.setNombre(crearProyectoDataTransferObject.getNombre());
    nuevoProyecto.setDescripcion(crearProyectoDataTransferObject.getDescripcion());
    nuevoProyecto.setFechaInicio(crearProyectoDataTransferObject.getFechaInicio());

    //
    return proyectoRepository.save(nuevoProyecto);
  }

  public List<Proyecto> obtenerProyectos() {
    return proyectoRepository.findAll();
  }
  public Optional<Proyecto> asignarLider(Long proyectoId, String liderId) {
    Optional<Proyecto> proyectoOptional = proyectoRepository.findById(proyectoId);
    // if project exists
    if (proyectoOptional.isPresent()) {
      Proyecto proyecto = proyectoOptional.get();
      proyecto.setLider(liderId); // Asign lider to project
      proyectoRepository.save(proyecto); // Save changes in Data Base
    }
    return proyectoOptional;
  }
}