package com.gestion_proyectos.api.service;

import com.gestion_proyectos.api.dto.CrearProyectoDataTransferObject;
import com.gestion_proyectos.api.entity.Proyecto;
import com.gestion_proyectos.api.entity.Usuario;
import com.gestion_proyectos.api.repository.ProyectoRepository;
import com.gestion_proyectos.api.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProyectoService {

  @Autowired
  private ProyectoRepository proyectoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Proyecto crearProyecto(CrearProyectoDataTransferObject crearProyectoDataTransferObject) {

    Proyecto nuevoProyecto = new Proyecto();
    nuevoProyecto.setNombre(crearProyectoDataTransferObject.getNombre());
    nuevoProyecto.setDescripcion(crearProyectoDataTransferObject.getDescripcion());
    nuevoProyecto.setFechaInicio(crearProyectoDataTransferObject.getFechaInicio());

    return proyectoRepository.save(nuevoProyecto);
  }

  public List<Proyecto> obtenerProyectos() {
    return proyectoRepository.findAll();
  }
  public Optional<Proyecto> asignarLider(Long proyectoId, Long liderId) {
    // Search project
    Optional<Proyecto> proyectoOptional = proyectoRepository.findById(proyectoId);

    //Search user, it will be assigned as new leader
    Optional<Usuario> usuarioOptional = usuarioRepository.findById(liderId);

    // if both exists, it connect it
    if (proyectoOptional.isPresent() && usuarioOptional.isPresent()) {
      Proyecto proyecto = proyectoOptional.get();
      Usuario nuevoLider = usuarioOptional.get();

      proyecto.setLider(nuevoLider); // Assign the user
      proyectoRepository.save(proyecto); // Save changes
      return Optional.of(proyecto);
    }
    // if not exists, return empty
    return Optional.empty(); // If not exists, return empty
  }

  public Optional<Proyecto> asignarDesarrolladores(Long proyectoId, List<Long> desarrolladoresIds) {
    // Search project
    Optional<Proyecto> proyectoOptional = proyectoRepository.findById(proyectoId);

    // if not exists, return empty
    if (proyectoOptional.isEmpty()) {
      return Optional.empty();
    }
     // Search all users by IDs
    List<Usuario> desarrolladores = usuarioRepository.findAllById(desarrolladoresIds);

    Proyecto proyecto = proyectoOptional.get(); //Asign the list od developers to the project
    proyecto.getDesarrolladores().addAll(desarrolladores); // use a set to avoid duplicates
    proyectoRepository.save(proyecto); // Save the project with the new developers
    return Optional.of(proyecto);
  }
}