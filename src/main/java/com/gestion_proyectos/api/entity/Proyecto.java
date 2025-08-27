package com.gestion_proyectos.api.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "tbl_proyectos")
public class Proyecto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String descripcion;
  private LocalDate fechaInicio;
  //private String lider;

  //Many to One with Usuario as Lider
  @ManyToOne
  @JoinColumn(name = "lider_id")
  private Usuario lider;

  //Many to many for developers
  @ManyToMany
  @JoinTable(
    name = "proyecto_desarrolladores",
    joinColumns = @JoinColumn(name = "proyecto_id"),
    inverseJoinColumns = @JoinColumn(name = "usuario_id")
  )
  private Set<Usuario> desarrolladores = new HashSet<>();

// Getters and Setter for all attributes
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public void setFechaInicio(LocalDate fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  //getters and setters for Lider
  public Usuario getLider() {
    return lider;
  }

  public void setLider(Usuario lider) {
    this.lider = lider;
  }

  //getters and setters for developers
  public Set<Usuario> getDesarrolladores() {
    return desarrolladores;
  }

  public void setDesarrolladores(Set<Usuario> desarrolladores) {
    this.desarrolladores = desarrolladores;
  }
}