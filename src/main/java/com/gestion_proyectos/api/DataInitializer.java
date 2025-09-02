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
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private RolRepository rolRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ProyectoRepository proyectoRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // Create rols if they do not exist
    Rol AdminRol = rolRepository.findByNombre("ADMIN").orElseGet(() -> {
      Rol rol = new Rol();
      rol.setNombre("ADMIN");
      return rolRepository.save(rol);
    });

    Rol LiderRol = rolRepository.findByNombre("LIDER").orElseGet(() -> {
      Rol rol = new Rol();
      rol.setNombre("LIDER");
      return rolRepository.save(rol);
    });

    rolRepository.findByNombre("DESARROLLADOR").orElseGet(() -> {
      Rol rol = new Rol();
      rol.setNombre("DESARROLLADOR");
      return rolRepository.save(rol);
    });

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
      Usuario admin = new Usuario();
      admin.setNombre("Roberto Palacios");
      admin.setEmail("roberto@example.com");
      admin.setPassword(passwordEncoder.encode("admin123")); // Password for admin Encrypted
      admin.setRoles(Set.of(AdminRol)); // Assign the found Rol to the Usuario
      usuarioRepository.save(admin);

      Usuario lider = new Usuario();
      lider.setNombre("Caterine Morales");
      lider.setEmail("caterine@example.com");
      lider.setPassword(passwordEncoder.encode("lider123")); // Password for lider Encrypted
      lider.setRoles(Set.of(LiderRol)); // Assign the found Rol to the Usuario
      usuarioRepository.save(lider);
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
