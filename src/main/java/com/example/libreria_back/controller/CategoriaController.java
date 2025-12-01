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

import com.example.libreria_back.entity.CategoriaEntity;
import com.example.libreria_back.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/listar")
	public ResponseEntity<List<CategoriaEntity>> listarCategorias() {
		List<CategoriaEntity> categorias = categoriaService.listarCategorias();
		return new ResponseEntity<>(categorias, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaEntity> listarCategoriaPorId(@PathVariable Long id) {
		CategoriaEntity categoria = categoriaService.buscarPorId(id);
		if (categoria != null) {
			return new ResponseEntity<>(categoria, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<CategoriaEntity> crearCategoria(@RequestBody CategoriaEntity categoria) {
		CategoriaEntity nuevaCategoria = categoriaService.crearCategoria(categoria);
		return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CategoriaEntity> updateCategoria(@PathVariable Long id,
			@RequestBody CategoriaEntity newCategoria) {
		CategoriaEntity categoriaActualizada = categoriaService.updateCategoria(id, newCategoria);
		if (categoriaActualizada != null) {
			return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
