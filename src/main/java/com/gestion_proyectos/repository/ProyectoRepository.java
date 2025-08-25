package com.gestion_proyectos.repository;

import org.springframework.stereotype.Repository;

import com.gestion_proyectos.entity.Proyecto;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Additional query methods can be defined here if needed
}
