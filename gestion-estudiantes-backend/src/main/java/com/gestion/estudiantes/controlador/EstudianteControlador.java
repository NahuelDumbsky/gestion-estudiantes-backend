package com.gestion.estudiantes.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.estudiantes.excepciones.ResourceNotFoundException;
import com.gestion.estudiantes.modelo.Estudiante;
import com.gestion.estudiantes.repositorio.EstudianteRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EstudianteControlador {
	
	@Autowired
	private EstudianteRepositorio repositorio;

	//este metodo sirve para listar todos los estudiantes
	@GetMapping("/estudiantes")
	public List<Estudiante> listarTodosLosEstudiantes(){
		return repositorio.findAll();
	}
	
	//este metodo sirve para guardar el estudiante
	@PostMapping("/estudiantes")
	public Estudiante guardarEstudiante(@RequestBody Estudiante estudiante) {
		return repositorio.save(estudiante);
	}
	
	//este metodo sirve para buscar un estudiante por id
	@GetMapping("/estudiantes/{id}")
	public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable Long id){
		Estudiante estudiante= repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(("El estudiante con el id " + id + " no existe")));
		return	ResponseEntity.ok(estudiante);
	}
	
	//este metodo actualiza un estudiante en la lista
	@PutMapping("/estudiantes/{id}")
	public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante detallesEstudiante){
		Estudiante estudiante= repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(("El estudiante con el id " + id + " no existe")));
		
		estudiante.setId(detallesEstudiante.getId());
		estudiante.setDni(detallesEstudiante.getDni());
		estudiante.setNombre(detallesEstudiante.getNombre());
		estudiante.setApellido(detallesEstudiante.getApellido());
		estudiante.setEmail(detallesEstudiante.getEmail());
		
		Estudiante estudianteActualizado= repositorio.save(estudiante);
		return	ResponseEntity.ok(estudianteActualizado);
	}
	
	@DeleteMapping("/estudiantes/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEstudiante(@PathVariable Long id){
		Estudiante estudiante = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el estudiante con el ID : " + id));
		
		repositorio.delete(estudiante);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
