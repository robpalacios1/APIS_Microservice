package com.gestion_proyectos.dto;

import java.time.LocalDate;

public class CrearProyectoDataTransferObject {

  private String nombre;
  private String descripcion;
  private LocalDate fechaInicio;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public LocalDate getFechaInicio() {
    return fechaInicio;
  }
  public void setFechaInicio(String fechaInicio) {
    this.fechaInicio = LocalDate.parse(fechaInicio);
  }

}
