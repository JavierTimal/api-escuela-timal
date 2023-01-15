package com.timal.app.escuela.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timal.app.escuela.models.Materia;

public interface IMateriasDao extends JpaRepository<Materia, Long>{

}
