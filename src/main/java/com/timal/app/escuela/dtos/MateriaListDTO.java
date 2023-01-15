package com.timal.app.escuela.dtos;

public class MateriaListDTO {
	private Long id;
	
	private String nombre;
	
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

	public MateriaListDTO(Long id, String nombre, Integer activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public MateriaListDTO() {
		super();
	}
	
	
}
