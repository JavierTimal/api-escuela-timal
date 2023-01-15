package com.timal.app.escuela.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timal.app.escuela.models.Alumno;

public interface IAlumnosDao extends JpaRepository<Alumno, Long>{

}
