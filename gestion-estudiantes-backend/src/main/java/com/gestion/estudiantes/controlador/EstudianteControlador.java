package com.gestion.estudiantes.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.estudiantes.modelo.Estudiante;
import com.gestion.estudiantes.repositorio.EstudianteRepositorio;

@RestController
@RequestMapping("/api/v1/")
public class EstudianteControlador {
	
	@Autowired
	private EstudianteRepositorio repositorio;

	@GetMapping("/estudiantes")
	public List<Estudiante> listarTodosLosEstudiantes(){
		return repositorio.findAll();
	}
}
