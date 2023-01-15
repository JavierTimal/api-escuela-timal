package com.timal.app.escuela.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timal.app.escuela.models.Rol;

public interface IRolDao extends JpaRepository<Rol, Long> {
	public Optional<Rol> findByNombre(String nombre);
}
