package com.gestion_proyectos.api.dto;

public class CrearUsuarioDataTransferObject {

  private String nombre;
  private String email;
  //Here we receive the "Rol" or "Developers"
  private String rol;

  // Getters and Setters
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }
}
