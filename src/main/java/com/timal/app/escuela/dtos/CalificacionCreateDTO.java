package com.timal.app.escuela.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CalificacionCreateDTO {

	private Long id;
	
	//@NotNull(message = "Id alumno es requerido")
	private Long alumno;
	
	//@NotNull(message = "Id materia es requerido")
	private Long materia;
	
	//@NotNull(message = "La calificacion es requerida")
	private Double calificacion;
	
	//@NotNull(message = "Fecha de registro es requerida")
	private Date fechaRegistro;

	public CalificacionCreateDTO(Long id, Long alumno, Long materia, Double calificacion, Date fechaRegistro) {
		super();
		this.id = id;
		this.alumno = alumno;
		this.materia = materia;
		this.calificacion = calificacion;
		this.fechaRegistro = fechaRegistro;
	}

	public CalificacionCreateDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAlumno() {
		return alumno;
	}

	public void setAlumno(Long alumno) {
		this.alumno = alumno;
	}

	public Long getMateria() {
		return materia;
	}

	public void setMateria(Long materia) {
		this.materia = materia;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	
	
	
}
