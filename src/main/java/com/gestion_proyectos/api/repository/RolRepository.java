package com.gestion_proyectos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion_proyectos.api.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
