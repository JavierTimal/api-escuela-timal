package com.timal.app.escuela.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timal.app.escuela.dtos.MateriaCreateDTO;
import com.timal.app.escuela.dtos.MateriaListDTO;
import com.timal.app.escuela.dtos.MensajeResponseDTO;
import com.timal.app.escuela.dtos.ObjResponse;
import com.timal.app.escuela.services.IService;
import com.timal.app.escuela.utils.AppConstantes;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "*")
public class MateriasController {
	@Autowired
	private IService<MateriaListDTO, MateriaCreateDTO> materiasService;
	
	@GetMapping
	public ObjResponse getListaMaterias(
			@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir){
		return materiasService.getAll(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
	}
	
		@GetMapping("/{id}")
		public ResponseEntity<MateriaListDTO> getMateriaById(@PathVariable(name = "id") long id){
			return ResponseEntity.ok(materiasService.getById(id));
		}
	
	@PostMapping
	public ResponseEntity<MateriaListDTO> guardarMateria(@Valid @RequestBody MateriaCreateDTO materiaDTO){
		return new ResponseEntity<>(materiasService.create(materiaDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MateriaListDTO> actualizarMateria(@Valid @RequestBody MateriaCreateDTO materiaDTO, @PathVariable(name = "id") long id){
		MateriaListDTO materiaRespuesta = materiasService.update(materiaDTO, id);
		return new ResponseEntity<>(materiaRespuesta, HttpStatus.NO_CONTENT);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MensajeResponseDTO> eliminarMateria(@PathVariable(name = "id") long id){
		materiasService.delete(id);
		return new ResponseEntity<>(new MensajeResponseDTO("Materia eliminado correctamente") , HttpStatus.OK);

		
	}
}
