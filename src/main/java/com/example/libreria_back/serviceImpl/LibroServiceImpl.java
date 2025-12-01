package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.LibroDao;
import com.example.libreria_back.entity.LibroEntity;
import com.example.libreria_back.entity.AutorEntity;
import com.example.libreria_back.entity.CategoriaEntity;
import com.example.libreria_back.entity.EditorialEntity;
import com.example.libreria_back.service.AutorService;
import com.example.libreria_back.service.CategoriaService;
import com.example.libreria_back.service.EditorialService;
import com.example.libreria_back.service.LibroService;

import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;

@Service
public class LibroServiceImpl implements LibroService {

	private final LibroDao libroDao;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private EditorialService editorialService;

	@Autowired
	public LibroServiceImpl(LibroDao libroDao) {
		this.libroDao = libroDao;
	}

	@Override
	@Transactional
	public List<LibroEntity> listarLibros() {
		// TODO Auto-generated method stub
		return libroDao.findAll();
	}

	@Override
	@Transactional
	public LibroEntity listarLibroPorId(Long id) {
		// TODO Auto-generated method stub
		return libroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<LibroEntity> buscarLibrosPorTitulo(String titulo) {
		// TODO Auto-generated method stub
		return libroDao.findByTitulo(titulo);
	}

	@Override
	@Transactional
	public LibroEntity crearLibro(LibroEntity libro) {
		// TODO Auto-generated method stub
		if (libro.getAutor() != null) {
			AutorEntity autor = autorService.listarAutorPorId(libro.getAutor().getIdAutor());
			libro.setAutor(autor);
		}

		if (libro.getCategoria() != null) {
			CategoriaEntity categoria = categoriaService.buscarPorId(libro.getCategoria().getIdCategoria());
			libro.setCategoria(categoria);
		}

		if (libro.getEditorial() != null) {
			EditorialEntity editorial = editorialService.listarEditorialPorId(libro.getEditorial().getIdEditorial());
			libro.setEditorial(editorial);
		}

		return libroDao.save(libro);
	}

	@Override
	@Transactional
	public LibroEntity updateLibro(Long id, LibroEntity newLibro) {
		try {
			// Verifica si el libro que se intenta actualizar existe en la base de datos
			LibroEntity libroExistente = libroDao.findById(id).orElse(null);

			if (libroExistente != null) {
				// Actualiza los campos básicos del libro
				if (newLibro.getTitulo() != null) {
					libroExistente.setTitulo(newLibro.getTitulo());
				}
				if (newLibro.getnPaginas() != null) {
					libroExistente.setnPaginas(newLibro.getnPaginas());
				}
				if (newLibro.getCodigo() != null) {
					libroExistente.setCodigo(newLibro.getCodigo());
				}
				if (newLibro.getPrecio() != null) {
					libroExistente.setPrecio(newLibro.getPrecio());
				}
				if (newLibro.getStock() != null) {
					libroExistente.setStock(newLibro.getStock());
				}
				

				// Actualiza los campos relacionados: Autor, Categoria y Editorial
				if (newLibro.getAutor() != null && newLibro.getAutor().getIdAutor() != null) {
					AutorEntity autor = autorService.listarAutorPorId(newLibro.getAutor().getIdAutor());
					libroExistente.setAutor(autor);
				}
				if (newLibro.getCategoria() != null && newLibro.getCategoria().getIdCategoria() != null) {
					CategoriaEntity categoria = categoriaService.buscarPorId(newLibro.getCategoria().getIdCategoria());
					libroExistente.setCategoria(categoria);
				}
				if (newLibro.getEditorial() != null && newLibro.getEditorial().getIdEditorial() != null) {
					EditorialEntity editorial = editorialService
							.listarEditorialPorId(newLibro.getEditorial().getIdEditorial());
					libroExistente.setEditorial(editorial);
				}

				// Guarda el libro actualizado en la base de datos
				return libroDao.save(libroExistente);
			} else {
				return null; // No se encontró el libro a actualizar
			}
		} catch (Exception e) {
			// Maneja la excepción aquí (por ejemplo, registra un error o lanza una
			// excepción personalizada)
			// Puedes personalizar el manejo de excepciones según tus necesidades
			e.printStackTrace(); // Imprime la excepción (debes considerar un manejo más apropiado)
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteLibro(Long id) {
		// TODO Auto-generated method stub
		libroDao.deleteById(id);
	}
	
	
	@Override
	@Transactional
	public List<LibroEntity> buscarLibroPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return libroDao.findByCodigo(codigo);
	}


}
