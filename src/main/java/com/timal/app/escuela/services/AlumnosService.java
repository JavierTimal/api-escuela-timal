package com.timal.app.escuela.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.timal.app.escuela.dao.IAlumnosDao;
import com.timal.app.escuela.dtos.AlumnoCreateDTO;
import com.timal.app.escuela.dtos.AlumnoListDTO;
import com.timal.app.escuela.dtos.ObjResponse;
import com.timal.app.escuela.dtos.MateriaDTO;
import com.timal.app.escuela.exceptions.ResourceNotFoundException;
import com.timal.app.escuela.models.Alumno;
import com.timal.app.escuela.models.AlumnoHasMateria;

@Service
public class AlumnosService implements IService<AlumnoListDTO ,AlumnoCreateDTO>{
	
	@Autowired
	private IAlumnosDao alumnosDao;

	@Override
	public AlumnoListDTO create(AlumnoCreateDTO alumnoDTO) {
		
		Alumno alumno = this.dtoToEntity(alumnoDTO);
		Alumno alumnoNuevo = alumnosDao.save(alumno);
		//return this.entityToDtoCreate(alumnoNuevo);
		return this.entityToDto(alumnoNuevo);
	}

	@Override
	public ObjResponse getAll(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
		
		Page<Alumno> alumnos = alumnosDao.findAll(pageable);
		List<Alumno> listaAlumnos = alumnos.getContent();
		
		
		List<AlumnoListDTO> contenido = listaAlumnos.stream().map(alumno -> this.entityToDto(alumno)).collect(Collectors.toList());
		ObjResponse<AlumnoListDTO> alumnosRespuesta = new ObjResponse();
		alumnosRespuesta.setContenido(contenido);
		alumnosRespuesta.setNumeroPagina(alumnos.getNumber());
		alumnosRespuesta.setMedidaPagina(alumnos.getSize());
		alumnosRespuesta.setTotalElementos(alumnos.getTotalElements());
		alumnosRespuesta.setTotalPaginas(alumnos.getTotalPages());
		alumnosRespuesta.setUltima(alumnos.isLast());

		return alumnosRespuesta;
	}

	@Override
	public AlumnoListDTO getById(long id) {
		// TODO Auto-generated method stub
		Alumno alumno = alumnosDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alumno","id",id));
		return this.entityToDto(alumno);
	}

	@Override
	public AlumnoListDTO update(AlumnoCreateDTO alumnoDTO, long id) {
		// TODO Auto-generated method stub
		Alumno alumno = alumnosDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alumno","id",id));
		alumno.setId(alumnoDTO.getId());
		alumno.setNombre(alumnoDTO.getNombre());
		alumno.setAp_paterno(alumnoDTO.getAp_paterno());
		alumno.setAp_materno(alumnoDTO.getAp_materno());
		alumno.setActivo(alumnoDTO.getActivo());
		Alumno alumnoActualizado = alumnosDao.save(alumno);
		return this.entityToDto(alumnoActualizado);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Alumno alumno = alumnosDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alumno","id",id));
		alumnosDao.delete(alumno);
	}
	
	public Alumno dtoToEntity(AlumnoCreateDTO alumnoDTO) {
		Alumno alumno = new Alumno();
		alumno.setId(alumnoDTO.getId());
		alumno.setNombre(alumnoDTO.getNombre());
		alumno.setAp_paterno(alumnoDTO.getAp_paterno());
		alumno.setAp_materno(alumnoDTO.getAp_materno());
		alumno.setActivo(alumnoDTO.getActivo());
		return alumno;
	}
	
	public AlumnoListDTO entityToDto(Alumno alumno) {
		Double suma = 0D;
		AlumnoListDTO alumnoDTO = new AlumnoListDTO();
		alumnoDTO.setId(alumno.getId());
		alumnoDTO.setNombre(alumno.getNombre());
		alumnoDTO.setAp_paterno(alumno.getAp_paterno());
		alumnoDTO.setAp_materno(alumno.getAp_materno());
		alumnoDTO.setActivo(alumno.getActivo());
		alumnoDTO.setPromedio(suma);
		if(alumno.getAlumnoMaterias() != null && alumno.getAlumnoMaterias().size() > 0) {
			for(AlumnoHasMateria ahm : alumno.getAlumnoMaterias()) {
				MateriaDTO m = new MateriaDTO();
				m.setIdReg(ahm.getId());
				m.setId(ahm.getMateria().getId());
				m.setMateria(ahm.getMateria().getNombre());
				m.setCalificacion(ahm.getCalificacion());
				m.setFechaRegistro(ahm.getFechaRegistro());
				alumnoDTO.addMateria(m);
				suma+= ahm.getCalificacion();
			}
			alumnoDTO.setPromedio(suma / alumno.getAlumnoMaterias().size());
		}
		
		
		return alumnoDTO;
	}
	
	/*public AlumnoCreateDTO entityToDtoCreate(Alumno alumno) {
		AlumnoCreateDTO alumnoDTO = new AlumnoCreateDTO();
		alumnoDTO.setId(alumno.getId());
		alumnoDTO.setNombre(alumno.getNombre());
		alumnoDTO.setAp_paterno(alumno.getAp_paterno());
		alumnoDTO.setAp_materno(alumno.getAp_materno());
		alumnoDTO.setActivo(alumno.getActivo());
		
		
		return alumnoDTO;
	}*/


}
