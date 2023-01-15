package com.timal.app.escuela.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_materias")
public class Materia {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator = "CUST_SEQ")
	@SequenceGenerator(sequenceName = "customer_seq", 
	allocationSize = 1, name = "CUST_SEQ")*/
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "id_t_materias", columnDefinition = "serial")
	@Column(name = "id_t_materias")
    private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "activo")
	private Integer activo;
	
	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private Set<AlumnoHasMateria> materiaAlumnos = new HashSet<>();

	public Materia() {
		super();
	}

	public Materia(Long id, String nombre, Integer activo, Set<AlumnoHasMateria> materiaAlumnos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.materiaAlumnos = materiaAlumnos;
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

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public Set<AlumnoHasMateria> getMateriaAlumnos() {
		return materiaAlumnos;
	}

	public void setMateriaAlumnos(Set<AlumnoHasMateria> materiaAlumnos) {
		this.materiaAlumnos = materiaAlumnos;
	}
	
	
}
