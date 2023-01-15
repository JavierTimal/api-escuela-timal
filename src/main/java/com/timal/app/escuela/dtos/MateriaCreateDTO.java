package com.timal.app.escuela.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MateriaCreateDTO {
	private Long id;
	
	@NotEmpty(message = "Nombre de la materia es requerido")
	private String nombre;
	
	@NotNull(message = "El estatus de la materia es requerido")
	private Integer activo;

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

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public MateriaCreateDTO() {
		super();
	}

	public MateriaCreateDTO(Long id, @NotEmpty(message = "Nombre de la materia es requerido") String nombre,
			@NotNull(message = "El estatus de la materia es requerido") Integer activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	
}
