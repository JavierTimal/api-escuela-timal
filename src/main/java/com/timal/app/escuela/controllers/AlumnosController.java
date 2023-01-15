package com.timal.app.escuela.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.timal.app.escuela.dtos.AlumnoCreateDTO;
import com.timal.app.escuela.dtos.AlumnoListDTO;
import com.timal.app.escuela.dtos.ObjResponse;
import com.timal.app.escuela.dtos.MensajeResponseDTO;
import com.timal.app.escuela.services.IService;
import com.timal.app.escuela.utils.AppConstantes;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*")
public class AlumnosController {
	@Autowired
	private IService<AlumnoListDTO, AlumnoCreateDTO> alumnosService;
	
	@GetMapping
	public ObjResponse getListaAlumnos(
			@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir){
		return alumnosService.getAll(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlumnoListDTO> getAlumnoById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(alumnosService.getById(id));
	}
	@PostMapping
	public ResponseEntity<AlumnoListDTO> guardarAlumno(@Valid @RequestBody AlumnoCreateDTO alumnoDTO){
		return new ResponseEntity<>(alumnosService.create(alumnoDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlumnoListDTO> actualizarAlumno(@Valid @RequestBody AlumnoCreateDTO alumnoDTO, @PathVariable(name = "id") long id){
		AlumnoListDTO alumnoResouesta = alumnosService.update(alumnoDTO, id);
		return new ResponseEntity<>(alumnoResouesta, HttpStatus.NO_CONTENT);

	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<MensajeResponseDTO> eliminarAlumno(@PathVariable(name = "id") long id){
		alumnosService.delete(id);
		return new ResponseEntity<>(new MensajeResponseDTO("Alumno eliminado correctamente") , HttpStatus.OK);

		
	}
}
