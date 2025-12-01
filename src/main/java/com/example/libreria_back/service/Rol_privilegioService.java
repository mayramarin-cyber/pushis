package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.Rol_privilegioEntity;

public interface Rol_privilegioService {

	 List<Rol_privilegioEntity> listarRol_privilegio();
	    Rol_privilegioEntity listarRol_privilegioPorId(Long id);
	    Rol_privilegioEntity crearRol_privilegio(Rol_privilegioEntity rol_privilegio);
	    Rol_privilegioEntity updateRol_privilegio(Long id, Rol_privilegioEntity newRol_privilegio);
	    void deleteRol_privilegio(Long id);
}
