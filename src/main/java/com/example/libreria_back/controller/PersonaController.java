package com.example.libreria_back.controller;

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
import com.example.libreria_back.service.PersonaService;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@GetMapping("/listar")
	public ResponseEntity<List<PersonaEntity>> listarPersonas() {
		List<PersonaEntity> personas = personaService.listarPersonas();
		return new ResponseEntity<>(personas, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonaEntity> listarPersonaPorId(@PathVariable Long id) {
		PersonaEntity persona = personaService.listarPersonaPorId(id);
		if (persona != null) {
			return new ResponseEntity<>(persona, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
		PersonaEntity newPersona = personaService.crearPersona(persona);
		return new ResponseEntity<>(newPersona, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PersonaEntity> updatePersona(@PathVariable Long id, @RequestBody PersonaEntity newPersona) {
		PersonaEntity personaActualizada = personaService.updatePersona(id, newPersona);
		if (personaActualizada != null) {
			return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable Long id) {
		personaService.deletePersona(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
