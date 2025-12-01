package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.PrivilegioEntity;
public interface PrivilegioService {
 
	List<PrivilegioEntity>listarPrivilegios();
	PrivilegioEntity listarPrivilegioPorId(long id);
	PrivilegioEntity crearPrivilegio(PrivilegioEntity privilegio);
	PrivilegioEntity updatePrivilegio(Long id, PrivilegioEntity newPrivilegio);
	void deletePrivilegio(Long id);
}
