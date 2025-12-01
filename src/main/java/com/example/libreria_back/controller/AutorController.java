package com.example.libreria_back.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria_back.entity.AutorEntity;
import com.example.libreria_back.service.AutorService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:51985")
@RestController
@RequestMapping("/api/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping("/listar")
	public ResponseEntity<List<AutorEntity>> listarAutores() {
		List<AutorEntity> autores = autorService.listarAutores();
		return new ResponseEntity<>(autores, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AutorEntity> listarAutorPorId(@PathVariable Long id) {
		AutorEntity autor = autorService.listarAutorPorId(id);
		if (autor != null) {
			return new ResponseEntity<>(autor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<AutorEntity> crearAutor(@RequestBody AutorEntity autor) {
		AutorEntity newAutor = autorService.crearAutor(autor);
		return new ResponseEntity<>(newAutor, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AutorEntity> updateAutor(@PathVariable Long id, @RequestBody AutorEntity newAutor) {
		AutorEntity autorActualizado = autorService.updateAutor(id, newAutor);
		if (autorActualizado != null) {
			return new ResponseEntity<>(autorActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteEliminar(@PathVariable Long id) {
		autorService.deleteAutor(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
