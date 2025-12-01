package com.example.libreria_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria_back.entity.PersonaEntity;
import com.example.libreria_back.entity.TrabajadorEntity;
import com.example.libreria_back.service.PersonaService;
import com.example.libreria_back.service.TrabajadorService;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorService;

	@Autowired
	private PersonaService personaService;

	@GetMapping("/listar")
	public ResponseEntity<List<TrabajadorEntity>> listarTrabajadores() {
		List<TrabajadorEntity> trabajadores = trabajadorService.listarTrabajadores();
		return new ResponseEntity<>(trabajadores, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TrabajadorEntity> listarTrabajadorPorId(@PathVariable Long id) {
		TrabajadorEntity trabajador = trabajadorService.listarTrabajadorPorId(id);
		if (trabajador != null) {
			return new ResponseEntity<>(trabajador, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<?> crearTrabajador(@RequestBody TrabajadorEntity trabajador) {
		try {
			if (trabajador.getPersona() != null) {
				trabajador.setPersona(personaService.listarPersonaPorId(trabajador.getPersona().getIdPersona()));
			} else {
				return new ResponseEntity<>("La persona no es válida o está ausente.", HttpStatus.BAD_REQUEST);
			}

			TrabajadorEntity nuevoTrabajador = trabajadorService.crearTrabajador(trabajador);

			return new ResponseEntity<>(nuevoTrabajador, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear el trabajador: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTrabajador(@PathVariable Long id, @RequestBody TrabajadorEntity newTrabajador) {
		try {
			TrabajadorEntity trabajadorExistente = trabajadorService.listarTrabajadorPorId(id);

			if (trabajadorExistente != null) {
				// Actualiza los campos básicos del trabajador
				if (newTrabajador.getCodigo() != null) {
					trabajadorExistente.setCodigo(newTrabajador.getCodigo());
				}
				if (newTrabajador.getEstadoLaboral() != null) {
					trabajadorExistente.setEstadoLaboral(newTrabajador.getEstadoLaboral());
				}
				if (newTrabajador.getEstado() != null) {
					trabajadorExistente.setEstado(newTrabajador.getEstado());
				}

				// Actualiza los campos relacionados: persona
				if (newTrabajador.getPersona() != null && newTrabajador.getPersona().getIdPersona() != null) {
					PersonaEntity persona = personaService
							.listarPersonaPorId(newTrabajador.getPersona().getIdPersona());
					trabajadorExistente.setPersona(persona);
				}

				TrabajadorEntity trabajadorActualizado = trabajadorService.updateTrabajador(id, trabajadorExistente);
				return new ResponseEntity<>(trabajadorActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Trabajador no encontrado.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al actualizar el trabajador: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteTrabajador(@PathVariable Long id) {
		trabajadorService.deleteTrabajador(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
