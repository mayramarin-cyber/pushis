package com.example.libreria_back.service;
import java.util.List;

import com.example.libreria_back.entity.LibroEntity;
public interface LibroService {
	
	  List<LibroEntity> listarLibros();
	    LibroEntity listarLibroPorId(Long id);
	    List<LibroEntity> buscarLibrosPorTitulo(String titulo);
	    LibroEntity crearLibro(LibroEntity libro);
	    LibroEntity updateLibro(Long id, LibroEntity newLibro);
	    void deleteLibro(Long id);
	    List<LibroEntity> buscarLibroPorCodigo(String codigo);

}
