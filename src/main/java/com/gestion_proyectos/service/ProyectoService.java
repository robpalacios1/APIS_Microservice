package com.gestion_proyectos.service;

import com.gestion_proyectos.entity.Proyecto;
import com.gestion_proyectos.repository.ProyectoRepository;
import com.gestion_proyectos.dto.CrearProyectoDataTransferObject;

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
}