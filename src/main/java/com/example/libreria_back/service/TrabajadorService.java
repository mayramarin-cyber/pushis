package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.TrabajadorEntity;

public interface TrabajadorService {

	 List<TrabajadorEntity> listarTrabajadores();
	    TrabajadorEntity listarTrabajadorPorId(Long id);
	 
	    TrabajadorEntity crearTrabajador(TrabajadorEntity trabajador);
	    TrabajadorEntity updateTrabajador(Long id, TrabajadorEntity newTrabajador);
	    void deleteTrabajador(Long id);
}
