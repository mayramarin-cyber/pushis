package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.PrivilegioDao;
import com.example.libreria_back.entity.PrivilegioEntity;
import com.example.libreria_back.service.PrivilegioService;

import jakarta.transaction.Transactional;

@Service
public class PrivilegioServiceImpl implements PrivilegioService{
	
	private final PrivilegioDao privilegioDao;

	@Autowired
	
	public PrivilegioServiceImpl(PrivilegioDao privilegioDao) {
		this.privilegioDao = privilegioDao;
	}
	
	@Override
	@Transactional
	public List<PrivilegioEntity> listarPrivilegios() {
		// TODO Auto-generated method stub
		return privilegioDao.findAll();
	}

	@Override
	@Transactional
	public PrivilegioEntity listarPrivilegioPorId(long id) {
		// TODO Auto-generated method stub
		return privilegioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PrivilegioEntity crearPrivilegio(PrivilegioEntity privilegio) {
		// TODO Auto-generated method stub
		return privilegioDao.save(privilegio);
	}

	@Override
	@Transactional
	public PrivilegioEntity updatePrivilegio(Long id, PrivilegioEntity newPrivilegio) {
		PrivilegioEntity privilegioExistente = privilegioDao.findById(id).orElse(null);

		if(privilegioExistente != null) {
			if (newPrivilegio.getNombre() != null) {
				privilegioExistente.setNombre(newPrivilegio.getNombre());
			}
			if (newPrivilegio.getEstado()!= null) {
				privilegioExistente.setEstado(newPrivilegio.getEstado());
			}
			
			return privilegioDao.save(privilegioExistente);
			
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void deletePrivilegio(Long id) {
		// TODO Auto-generated method stub
		privilegioDao.deleteById(id);
	}

}
