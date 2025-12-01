package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.CategoriaEntity;

public interface CategoriaService {

	List<CategoriaEntity>listarCategorias();
	CategoriaEntity buscarPorId(long id);
	CategoriaEntity crearCategoria(CategoriaEntity categoria);
	CategoriaEntity updateCategoria(Long id, CategoriaEntity newCategoria);
	void deleteCategoria(Long id);
}
