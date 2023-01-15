package com.timal.app.escuela.dtos;

import java.util.Set;

import com.timal.app.escuela.models.AlumnoHasMateria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AlumnoCreateDTO {
	private Long id;
	
	@NotEmpty(message = "Nombre del alumno es requerido")
	String nombre;
	
	@NotEmpty(message = "Ap Paterno del alumno es requerido")
	String ap_paterno;
	
	@NotEmpty(message = "Ap Materno del alumno es requerido")
	String ap_materno;
	
	@Column(name = "activo")
	@NotNull(message = "El estatus del alumno es requerido")
	Integer activo;

	public AlumnoCreateDTO() {
		super();
	}

	public AlumnoCreateDTO(Long id, @NotEmpty(message = "Nombre del alumno es requerido") String nombre,
			@NotEmpty(message = "Ap Paterno del alumno es requerido") String ap_paterno,
			@NotEmpty(message = "Ap Materno del alumno es requerido") String ap_materno,
			@NotEmpty(message = "El estatus del alumno es requerido") Integer activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ap_paterno = ap_paterno;
		this.ap_materno = ap_materno;
		this.activo = activo;
	}

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

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
	
}
