package com.example.libreria_back.service;
import java.util.List;

import com.example.libreria_back.entity.AutorEntity;

public interface AutorService {

	List<AutorEntity>listarAutores();
	AutorEntity listarAutorPorId(long id);
	AutorEntity crearAutor(AutorEntity autor);
	AutorEntity updateAutor(Long id, AutorEntity newAutor);
	void deleteAutor(Long id);
}
