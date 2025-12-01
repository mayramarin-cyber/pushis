package com.example.libreria_back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libreria_back.entity.LibroEntity;

public interface LibroDao extends JpaRepository<LibroEntity, Long>{
	  List<LibroEntity> findByTitulo(String titulo);
	  List<LibroEntity> findByCodigo(String codigo);

}
