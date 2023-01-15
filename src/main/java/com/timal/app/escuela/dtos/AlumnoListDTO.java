package com.timal.app.escuela.dtos;

import java.util.ArrayList;
import java.util.List;


public class AlumnoListDTO {
	
	private Long id;
	
	private String nombre;
	
	private String ap_paterno;
	
	private String ap_materno;
	
	private Integer activo;
	
    private List<MateriaDTO> materias;
    
    private Double promedio;
    
    public void addMateria(MateriaDTO m){
    	this.materias.add(m);
    }

	public AlumnoListDTO() {
		super();
		materias = new ArrayList<>();
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
	

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}

	public List<MateriaDTO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaDTO> materias) {
		this.materias = materias;
	}

	public AlumnoListDTO(Long id, String nombre, String ap_paterno, String ap_materno, Integer activo,
			List<MateriaDTO> materias, Double promedio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ap_paterno = ap_paterno;
		this.ap_materno = ap_materno;
		this.activo = activo;
		this.materias = materias;
		this.promedio = promedio;
	}
	
	
    
    
}
