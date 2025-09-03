package com.gestion_proyectos.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de inicio de sesión")
public class LoginRequest {

  @Schema(description = "Email del usuario", example = "admin@example.com", required = true)
  private String email;

  @Schema(description = "Contraseña del usuario", example = "admin123", required = true)
  private String password;

  // Constructors
  public LoginRequest() {
  }

  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  // Getters and Setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginRequest{" +
        "email='" + email + '\'' +
        ", password='[PROTECTED]'" +
        '}';
  }
}
