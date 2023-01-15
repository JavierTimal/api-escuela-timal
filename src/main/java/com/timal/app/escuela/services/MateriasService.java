package com.timal.app.escuela.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.timal.app.escuela.dao.IMateriasDao;
import com.timal.app.escuela.dtos.MateriaCreateDTO;
import com.timal.app.escuela.dtos.MateriaListDTO;
import com.timal.app.escuela.dtos.ObjResponse;
import com.timal.app.escuela.exceptions.ResourceNotFoundException;
import com.timal.app.escuela.models.Materia;

@Service
public class MateriasService implements IService<MateriaListDTO ,MateriaCreateDTO> {

	@Autowired
	private IMateriasDao materiasDao;
	
	@Override
	public MateriaListDTO create(MateriaCreateDTO obj) {
		Materia materia = this.dtoToEntity(obj);
		Materia materiaNueva = materiasDao.save(materia);
		//return this.entityToDtoCreate(alumnoNuevo);
		return this.entityToDto(materiaNueva);
	}

	@Override
	public ObjResponse getAll(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
		
		Page<Materia> materias = materiasDao.findAll(pageable);
		List<Materia> listaMaterias = materias.getContent();
		
		
		List<MateriaListDTO> contenido = listaMaterias.stream().map(materia -> this.entityToDto(materia)).collect(Collectors.toList());
		ObjResponse<MateriaListDTO> materiasRespuesta = new ObjResponse();
		materiasRespuesta.setContenido(contenido);
		materiasRespuesta.setNumeroPagina(materias.getNumber());
		materiasRespuesta.setMedidaPagina(materias.getSize());
		materiasRespuesta.setTotalElementos(materias.getTotalElements());
		materiasRespuesta.setTotalPaginas(materias.getTotalPages());
		materiasRespuesta.setUltima(materias.isLast());

		return materiasRespuesta;
	}

	@Override
	public MateriaListDTO getById(long id) {
		// TODO Auto-generated method stub
		Materia materia = materiasDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Materia","id",id));
		return this.entityToDto(materia);
	}

	@Override
	public MateriaListDTO update(MateriaCreateDTO materiaDTO, long id) {
		// TODO Auto-generated method stub
		Materia materia = materiasDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Materia","id",id));
		materia.setId(materiaDTO.getId());
		materia.setNombre(materiaDTO.getNombre());
		materia.setActivo(materiaDTO.getActivo());
		Materia materiaActualizada = materiasDao.save(materia);
		return this.entityToDto(materiaActualizada);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Materia materia = materiasDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Materia","id",id));
		materiasDao.delete(materia);
	}
	
	public Materia dtoToEntity(MateriaCreateDTO materiaDTO) {
		Materia materia = new Materia();
		materia.setId(materiaDTO.getId());
		materia.setNombre(materiaDTO.getNombre());
		materia.setActivo(materiaDTO.getActivo());
		return materia;
	}
	
	public MateriaListDTO entityToDto(Materia materia) {
		MateriaListDTO materiaDTO = new MateriaListDTO();
		materiaDTO.setId(materia.getId());
		materiaDTO.setNombre(materia.getNombre());
		materiaDTO.setActivo(materia.getActivo());
		return materiaDTO;
	}

}
