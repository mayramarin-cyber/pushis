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

import com.example.libreria_back.service.EditorialService;
import com.example.libreria_back.entity.EditorialEntity;
import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

	@Autowired
	private EditorialService editorialService;

	@GetMapping("/listar")
	public ResponseEntity<List<EditorialEntity>> listarEditoriales() {
		List<EditorialEntity> editoriales = editorialService.listarEditoriales();
		return new ResponseEntity<>(editoriales, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<EditorialEntity> listarEditorialPorId(@PathVariable Long id) {
		EditorialEntity editorial = editorialService.listarEditorialPorId(id);
		if (editorial != null) {
			return new ResponseEntity<>(editorial, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<EditorialEntity> crearEditorial(@RequestBody EditorialEntity editorial) {
		EditorialEntity nuevaEditorial = editorialService.crearEditorial(editorial);
		return new ResponseEntity<>(nuevaEditorial, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<EditorialEntity> updateEditorial(@PathVariable Long id,
			@RequestBody EditorialEntity newEditorial) {
		EditorialEntity editorialActualizada = editorialService.updateEditorial(id, newEditorial);
		if (editorialActualizada != null) {
			return new ResponseEntity<>(editorialActualizada, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteEliminar(@PathVariable Long id) {
		editorialService.deleteEditorial(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
