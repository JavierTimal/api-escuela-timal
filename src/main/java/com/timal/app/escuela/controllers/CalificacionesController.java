package com.timal.app.escuela.controllers;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.timal.app.escuela.dtos.CalificacionCreateDTO;
import com.timal.app.escuela.dtos.CalificacionesListDTO;
import com.timal.app.escuela.dtos.MateriaCreateDTO;
import com.timal.app.escuela.dtos.MateriaListDTO;
import com.timal.app.escuela.dtos.MensajeResponseDTO;
import com.timal.app.escuela.services.IService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionesController {

	@Autowired
	private IService<CalificacionesListDTO, CalificacionCreateDTO> calificacionesService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CalificacionesListDTO> getMateriaById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(calificacionesService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> guardarCalificacion(@Valid @RequestBody CalificacionCreateDTO calificacionDTO){
		calificacionesService.create(calificacionDTO);
		Map<String, String> response = new HashMap<>();
		response.put("success", "ok");
		response.put("msg", "calificacion registrada");
		//return new ResponseEntity<>(calificacionesService.create(calificacionDTO), HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, String>> actualizarCalificacion(@Valid @RequestBody CalificacionCreateDTO calificacionDTO, @PathVariable(name = "id") long id){
		calificacionesService.update(calificacionDTO, id);
		Map<String, String> response = new HashMap<>();
		response.put("success", "ok");
		response.put("msg", "calificacion actualizada");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> eliminarAlumno(@PathVariable(name = "id") long id){
		calificacionesService.delete(id);
		Map<String, String> response = new HashMap<>();
		response.put("success", "ok");
		response.put("msg", "calificacion eliminada");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
