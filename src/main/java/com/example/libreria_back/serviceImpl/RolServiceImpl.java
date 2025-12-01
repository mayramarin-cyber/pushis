package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.RolDao;
import com.example.libreria_back.entity.RolEntity;
import com.example.libreria_back.service.RolService;

import jakarta.transaction.Transactional;

@Service
public class RolServiceImpl implements RolService {

	private final RolDao rolDao;

	@Autowired

	public RolServiceImpl(RolDao rolDao) {
		this.rolDao = rolDao;
	}

	@Override
	@Transactional
	public List<RolEntity> listarRoles() {
		// TODO Auto-generated method stub
		return rolDao.findAll();
	}

	@Override
	@Transactional
	public RolEntity listarRolesPorId(long id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public RolEntity crearRol(RolEntity rol) {
		// TODO Auto-generated method stub
		return rolDao.save(rol);
	}

	@Override
	@Transactional
	public RolEntity updateRol(Long id, RolEntity newRol) {
		RolEntity rolExistente = rolDao.findById(id).orElse(null);

		if (rolExistente != null) {
			if (newRol.getNombre() != null) {
				rolExistente.setNombre(newRol.getNombre());
			}

			return rolDao.save(rolExistente);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteRol(Long id) {
		rolDao.deleteById(id);

	}

}
