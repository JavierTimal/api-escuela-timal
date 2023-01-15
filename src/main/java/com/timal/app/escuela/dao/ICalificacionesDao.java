package com.timal.app.escuela.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timal.app.escuela.models.AlumnoHasMateria;

public interface ICalificacionesDao extends JpaRepository<AlumnoHasMateria, Long> {

}
