package com.example.libreria_back.service;
import java.util.List;

import com.example.libreria_back.entity.RolEntity;
public interface RolService {

	List<RolEntity>listarRoles();
	RolEntity listarRolesPorId(long id);
	RolEntity crearRol(RolEntity rol);
	RolEntity updateRol(Long id, RolEntity newRol);
	void deleteRol(Long id);
}
