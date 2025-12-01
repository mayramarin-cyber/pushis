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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria_back.entity.LibroEntity;
import com.example.libreria_back.service.AutorService;
import com.example.libreria_back.service.CategoriaService;
import com.example.libreria_back.service.EditorialService;
import com.example.libreria_back.service.LibroService;
import com.example.libreria_back.entity.AutorEntity;
import com.example.libreria_back.entity.CategoriaEntity;
import com.example.libreria_back.entity.EditorialEntity;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Libros", description = "API de gestión de libros")
@RestController
@RequestMapping("/api/libros")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private EditorialService editorialService;

	@GetMapping("/listar")
	public ResponseEntity<List<LibroEntity>> listarLibros() {

		List<LibroEntity> libros = libroService.listarLibros();
		return new ResponseEntity<>(libros, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LibroEntity> listarLibroPorId(@PathVariable Long id) {
		LibroEntity libro = libroService.listarLibroPorId(id);
		if (libro != null) {
			return new ResponseEntity<>(libro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/buscarPorTitulo")
	public ResponseEntity<List<LibroEntity>> buscarLibrosPorTitulo(@RequestParam String titulo) {
		List<LibroEntity> libros = libroService.buscarLibrosPorTitulo(titulo);
		return new ResponseEntity<>(libros, HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<?> crearLibro(@RequestBody LibroEntity libro) {
		try {
			if (libro.getCategoria() == null || libro.getCategoria().getIdCategoria() == null) {
				return new ResponseEntity<>("La categoría no es válida o está ausente.", HttpStatus.BAD_REQUEST);
			}

			CategoriaEntity categoria = categoriaService.buscarPorId(libro.getCategoria().getIdCategoria());
			if (categoria == null) {
				return new ResponseEntity<>("La categoría proporcionada no es válida.", HttpStatus.BAD_REQUEST);
			}
			libro.setCategoria(categoria);

			if (libro.getEditorial() != null && libro.getEditorial().getIdEditorial() != null) {
				EditorialEntity editorial = editorialService
						.listarEditorialPorId(libro.getEditorial().getIdEditorial());
				if (editorial == null) {
					return new ResponseEntity<>("La editorial proporcionada no es válida.", HttpStatus.BAD_REQUEST);
				}
				libro.setEditorial(editorial);
			} else {
				libro.setEditorial(null);
			}

			if (libro.getAutor() != null && libro.getAutor().getIdAutor() != null) {
				AutorEntity autor = autorService.listarAutorPorId(libro.getAutor().getIdAutor());
				if (autor == null) {
					return new ResponseEntity<>("El autor proporcionado no es válido.", HttpStatus.BAD_REQUEST);
				}
				libro.setAutor(autor);
			} else {
				libro.setAutor(null);
			}

			LibroEntity nuevoLibro = libroService.crearLibro(libro);

			return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear el libro: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateLibro(@PathVariable Long id, @RequestBody LibroEntity newLibro) {
		try {
			if (newLibro.getTitulo() == null || newLibro.getTitulo().isEmpty()) {
				return new ResponseEntity<>("El título del libro no es válido.", HttpStatus.BAD_REQUEST);
			}

			if (newLibro.getAutor() != null && newLibro.getAutor().getIdAutor() != null) {
				AutorEntity autor = autorService.listarAutorPorId(newLibro.getAutor().getIdAutor());
				if (autor == null) {
					return new ResponseEntity<>("El autor proporcionado no es válido.", HttpStatus.BAD_REQUEST);
				}
				newLibro.setAutor(autor);
			} else {
				newLibro.setAutor(null);
			}

			if (newLibro.getCategoria() != null && newLibro.getCategoria().getIdCategoria() != null) {
				CategoriaEntity categoria = categoriaService.buscarPorId(newLibro.getCategoria().getIdCategoria());
				if (categoria == null) {
					return new ResponseEntity<>("La categoría proporcionada no es válida.", HttpStatus.BAD_REQUEST);
				}
				newLibro.setCategoria(categoria);
			} else {
				newLibro.setCategoria(null);
			}

			if (newLibro.getEditorial() != null && newLibro.getEditorial().getIdEditorial() != null) {
				EditorialEntity editorial = editorialService
						.listarEditorialPorId(newLibro.getEditorial().getIdEditorial());
				if (editorial == null) {
					return new ResponseEntity<>("La editorial proporcionada no es válida.", HttpStatus.BAD_REQUEST);
				}
				newLibro.setEditorial(editorial);
			} else {
				newLibro.setEditorial(null);
			}

			LibroEntity libroActualizado = libroService.updateLibro(id, newLibro);
			if (libroActualizado != null) {
				return new ResponseEntity<>(libroActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al actualizar el libro: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteLibro(@PathVariable Long id) {
		libroService.deleteLibro(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/buscarPorCodigo")
	public ResponseEntity<List<LibroEntity>> buscarLibroPorCodigo(@RequestParam String codigo) {
		List<LibroEntity> libros = libroService.buscarLibroPorCodigo(codigo);
		return new ResponseEntity<>(libros, HttpStatus.OK);
	}

}
