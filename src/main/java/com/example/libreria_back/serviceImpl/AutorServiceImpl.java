package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.AutorDao;
import com.example.libreria_back.entity.AutorEntity;
import com.example.libreria_back.service.AutorService;

import jakarta.transaction.Transactional;

@Service
public class AutorServiceImpl implements AutorService{
	
	private final AutorDao autorDao;

	public AutorServiceImpl(AutorDao autorDao) {
		this.autorDao = autorDao;
	}
	

	@Override
	@Transactional
	public List<AutorEntity> listarAutores() {
		// TODO Auto-generated method stub
		return autorDao.findAll();
	}

	@Override
	@Transactional
	public AutorEntity listarAutorPorId(long id) {
		// TODO Auto-generated method stub
		return autorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public AutorEntity crearAutor(AutorEntity autor) {
		// TODO Auto-generated method stub
		return autorDao.save(autor);
	}

	@Override
	@Transactional
	public AutorEntity updateAutor(Long id, AutorEntity newAutor) {
		AutorEntity autorExistente = autorDao.findById(id).orElse(null);

		if(autorExistente != null) {
			if (newAutor.getNombre() != null) {
				autorExistente.setNombre(newAutor.getNombre());
			}
			if (newAutor.getEstado()!= null) {
				autorExistente.setEstado(newAutor.getEstado());
			}
			
			return autorDao.save(autorExistente);
			
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteAutor(Long id) {
		// TODO Auto-generated method stub
		autorDao.deleteById(id);
	}

}
