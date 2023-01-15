package com.timal.app.escuela.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_calificaciones")
public class AlumnoHasMateria {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator = "CUST_SEQ3")
	@SequenceGenerator(sequenceName = "customer_seq3", 
	allocationSize = 1, name = "CUST_SEQ3")*/
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "id_t_calificaciones", columnDefinition = "serial")
	@Column(name = "id_t_calificaciones")
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_t_usuarios")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_t_materias")
    private Materia materia;
    
    @Column(name = "calificacion")
    private Double calificacion;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

	public AlumnoHasMateria() {
		super();
	}

	public AlumnoHasMateria(Long id, Alumno alumno, Materia materia, Double calificacion, Date fechaRegistro) {
		super();
		this.id = id;
		this.alumno = alumno;
		this.materia = materia;
		this.calificacion = calificacion;
		this.fechaRegistro = fechaRegistro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
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
