package com.gestion_proyectos.api.dto;

public class LoginResponse {

  private final String jwt;

  public LoginResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }
}
