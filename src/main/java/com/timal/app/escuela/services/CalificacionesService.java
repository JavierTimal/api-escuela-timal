package com.timal.app.escuela.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timal.app.escuela.dao.IAlumnosDao;
import com.timal.app.escuela.dao.ICalificacionesDao;
import com.timal.app.escuela.dao.IMateriasDao;
import com.timal.app.escuela.dtos.AlumnoCreateDTO;
import com.timal.app.escuela.dtos.AlumnoListDTO;
import com.timal.app.escuela.dtos.CalificacionCreateDTO;
import com.timal.app.escuela.dtos.CalificacionesListDTO;
import com.timal.app.escuela.dtos.MateriaDTO;
import com.timal.app.escuela.dtos.ObjResponse;
import com.timal.app.escuela.exceptions.ResourceNotFoundException;
import com.timal.app.escuela.models.Alumno;
import com.timal.app.escuela.models.AlumnoHasMateria;
import com.timal.app.escuela.models.Materia;

@Service
public class CalificacionesService implements IService<CalificacionesListDTO, CalificacionCreateDTO>{

	@Autowired
	private IAlumnosDao alumnosDao;
	
	@Autowired
	private IMateriasDao materiasDao;
	
	@Autowired
	private ICalificacionesDao calificacionesDao;
	
	@Override
	public CalificacionesListDTO create(CalificacionCreateDTO calificacionDTO) {
		AlumnoHasMateria calificacion = this.dtoToEntity(calificacionDTO);
		AlumnoHasMateria calificacionNueva = calificacionesDao.save(calificacion);
		//return this.entityToDtoCreate(alumnoNuevo);
		return this.entityToDto(calificacionNueva);
		//return calificacionDTO;
	}

	@Override
	public ObjResponse getAll(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalificacionesListDTO getById(long id) {
		// TODO Auto-generated method stub
		AlumnoHasMateria calificacion = calificacionesDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("AlumnoHasMateria","id",id));
		return this.entityToDto(calificacion);
	}

	@Override
	public CalificacionesListDTO update(CalificacionCreateDTO calificacionDTO, long id) {
		// TODO Auto-generated method stub
		AlumnoHasMateria calificacion = calificacionesDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("AlumnoHasMateria","id",id));
		calificacion.setId(calificacionDTO.getId());
		calificacion.setAlumno(alumnosDao.findById(calificacionDTO.getAlumno()).orElse(null));
		calificacion.setMateria(materiasDao.findById(calificacionDTO.getMateria()).orElse(null));
		calificacion.setCalificacion(calificacionDTO.getCalificacion());
		//calificacion.setFechaRegistro(calificacionDTO.getFechaRegistro());
		calificacion.setFechaRegistro(new Date());
		AlumnoHasMateria calificacionActualizada = calificacionesDao.save(calificacion);
		return this.entityToDto(calificacionActualizada);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		AlumnoHasMateria calificacion = calificacionesDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("AlumnoHasMateria","id",id));
		calificacionesDao.delete(calificacion);
	}
	
	public AlumnoHasMateria dtoToEntity(CalificacionCreateDTO calificacionDTO) {
		AlumnoHasMateria calificacion = new AlumnoHasMateria();
		//calificacion.setId(calificacionDTO.getId());
		calificacion.setAlumno(alumnosDao.findById(calificacionDTO.getAlumno()).orElse(null));
		calificacion.setMateria(materiasDao.findById(calificacionDTO.getMateria()).orElse(null));
		calificacion.setCalificacion(calificacionDTO.getCalificacion());
		//calificacion.setFechaRegistro(calificacionDTO.getFechaRegistro());
		calificacion.setFechaRegistro(new Date());
		return calificacion;
	}
	
	public CalificacionesListDTO entityToDto(AlumnoHasMateria calificacion) {
		CalificacionesListDTO calificacionDTO = new CalificacionesListDTO();
		calificacionDTO.setId(calificacion.getId());
		calificacionDTO.setCalificacion(calificacion.getCalificacion());
		calificacionDTO.setMateria(calificacion.getMateria().getNombre());
		calificacionDTO.setAlumno(calificacion.getAlumno().getNombre());
		calificacionDTO.setFechaRegistro(calificacion.getFechaRegistro());
		
		
		
		return calificacionDTO;
	}

}
