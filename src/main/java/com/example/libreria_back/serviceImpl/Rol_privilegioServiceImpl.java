package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.Rol_privilegioDao;
import com.example.libreria_back.entity.PrivilegioEntity;
import com.example.libreria_back.entity.RolEntity;
import com.example.libreria_back.entity.Rol_privilegioEntity;
import com.example.libreria_back.service.PrivilegioService;
import com.example.libreria_back.service.RolService;
import com.example.libreria_back.service.Rol_privilegioService;

import jakarta.transaction.Transactional;

@Service
public class Rol_privilegioServiceImpl implements Rol_privilegioService{

	private final Rol_privilegioDao rol_privilegioDao;

	@Autowired
	private RolService rolService;

	@Autowired
	private PrivilegioService privilegioService;


	@Autowired
	public Rol_privilegioServiceImpl(Rol_privilegioDao rol_privilegioDao) {
		this.rol_privilegioDao = rol_privilegioDao;
	}
	
	
	@Override
	@Transactional
	public List<Rol_privilegioEntity> listarRol_privilegio() {
		// TODO Auto-generated method stub
		return rol_privilegioDao.findAll();
	}

	@Override
	@Transactional
	public Rol_privilegioEntity listarRol_privilegioPorId(Long id) {
		// TODO Auto-generated method stub
		return rol_privilegioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Rol_privilegioEntity crearRol_privilegio(Rol_privilegioEntity rol_privilegio) {
		if (rol_privilegio.getRol() != null) {
			RolEntity rol = rolService.listarRolesPorId(rol_privilegio.getRol().getIdRol());
			rol_privilegio.setRol(rol);
		}

		if (rol_privilegio.getPrivilegio() != null) {
			PrivilegioEntity privilegio = privilegioService.listarPrivilegioPorId(rol_privilegio.getPrivilegio().getIdPrivilegio());
			rol_privilegio.setPrivilegio(privilegio);
		}

		return rol_privilegioDao.save(rol_privilegio);
	}

	@Override
	@Transactional
	public Rol_privilegioEntity updateRol_privilegio(Long id, Rol_privilegioEntity newRol_privilegio) {
		try {
			// Verifica si el rolprivilegio que se intenta actualizar existe en la base de datos
			Rol_privilegioEntity rol_privilegioExistente = rol_privilegioDao.findById(id).orElse(null);

			if (rol_privilegioExistente != null) {
				
				
				// Actualiza los campos relacionados: rol, privilegio
				if (newRol_privilegio.getRol() != null && newRol_privilegio.getRol().getIdRol() != null) {
					RolEntity rol = rolService.listarRolesPorId(newRol_privilegio.getRol().getIdRol());
					rol_privilegioExistente.setRol(rol);
				}
				if (newRol_privilegio.getPrivilegio() != null && newRol_privilegio.getPrivilegio().getIdPrivilegio() != null) {
					PrivilegioEntity privilegio = privilegioService.listarPrivilegioPorId(newRol_privilegio.getPrivilegio().getIdPrivilegio());
					rol_privilegioExistente.setPrivilegio(privilegio);
				}

				// Guarda el Rol_privilegio actualizado en la base de datos
				return rol_privilegioDao.save(rol_privilegioExistente);
			} else {
				return null; // No se encontró el Rol_privilegio a actualizar
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
	public void deleteRol_privilegio(Long id) {
		rol_privilegioDao.deleteById(id);
		
	}

}
