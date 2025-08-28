package com.gestion_proyectos.api;

import org.springframework.beans.factory.annotation.Autowired;
import com.gestion_proyectos.api.repository.ProyectoRepository;
import com.gestion_proyectos.api.repository.RolRepository;
import com.gestion_proyectos.api.repository.UsuarioRepository;
import com.gestion_proyectos.api.entity.Proyecto;
import com.gestion_proyectos.api.entity.Rol;
import com.gestion_proyectos.api.entity.Usuario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private RolRepository rolRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ProyectoRepository proyectoRepository;

  @Override
  public void run(String... args) throws Exception {
    // Create rols if they do not exist
    if (rolRepository.count() == 0) {
      Rol rolLider = new Rol();
      rolLider.setNombre("LIDER");
      rolRepository.save(rolLider);

      Rol rolDesarrollador = new Rol();
      rolDesarrollador.setNombre("DESARROLLADOR");
      rolRepository.save(rolDesarrollador);
    }

    // Create Users if they do not exist
    if (usuarioRepository.count() == 0) {
      Usuario usuario1 = new Usuario();
      usuario1.setNombre("Roberto Palacios");
      usuario1.setEmail("roberto@example.com");
      usuarioRepository.save(usuario1);

      Usuario usuario2 = new Usuario();
      usuario2.setNombre("Caterine Morales");
      usuario2.setEmail("caterine@example.com");
      usuarioRepository.save(usuario2);
    }

    // Create Projects if they do not exist
    if (proyectoRepository.count() == 0) {
      Proyecto proyecto = new Proyecto();
      proyecto.setNombre("Empezar Proyecto");
      proyecto.setDescripcion("Descripcion del Proyecto Inicial");
      proyectoRepository.save(proyecto);
    }
  }
}
