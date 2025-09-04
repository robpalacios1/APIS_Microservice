package com.gestion_proyectos.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion_proyectos.api.dto.CrearUsuarioDataTransferObject;
import com.gestion_proyectos.api.entity.Rol;
import com.gestion_proyectos.api.repository.RolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private RolRepository rolRepository;

  @BeforeEach
  void setUp() {
    // Asegurar que el rol DESARROLLADOR existe
    if (rolRepository.findByNombre("DESARROLLADOR").isEmpty()) {
      Rol rolDesarrollador = new Rol();
      rolDesarrollador.setNombre("DESARROLLADOR");
      rolRepository.save(rolDesarrollador);
    }
  }

  @Test
  @WithMockUser(roles = "ADMIN") // Simula un usuario logueado con rol ADMIN
  void registrarUsuarioComoAdminDeberiaFuncionar() throws Exception {
    CrearUsuarioDataTransferObject dto = new CrearUsuarioDataTransferObject();
    dto.setNombre("Nuevo Dev");
    dto.setEmail("dev@example.com");
    dto.setRol("DESARROLLADOR");

    mockMvc.perform(post("/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre").value("Nuevo Dev"));
  }

  @Test
  @WithMockUser(roles = "LIDER") // Simula un usuario logueado con rol LIDER
  void registrarUsuarioComoLiderDeberiaFallar() throws Exception {
    CrearUsuarioDataTransferObject dto = new CrearUsuarioDataTransferObject();
    dto.setNombre("Otro Dev");
    dto.setEmail("otrodev@example.com");
    dto.setRol("DESARROLLADOR");

    mockMvc.perform(post("/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isForbidden()); // Esperamos un 403 Forbidden
  }
}
