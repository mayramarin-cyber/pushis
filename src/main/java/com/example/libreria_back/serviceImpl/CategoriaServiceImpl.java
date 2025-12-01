package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.CategoriaDao;
import com.example.libreria_back.entity.CategoriaEntity;
import com.example.libreria_back.service.CategoriaService;

import jakarta.transaction.Transactional;

@Service

public class CategoriaServiceImpl implements CategoriaService {
	
	private final CategoriaDao categoriaDao;

	public CategoriaServiceImpl(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	
	@Override
	@Transactional
	public List<CategoriaEntity> listarCategorias() {
		// TODO Auto-generated method stub
		return categoriaDao.findAll();
	}
	


	@Override
	@Transactional
	public CategoriaEntity buscarPorId(long id) {
		// TODO Auto-generated method stub
		return categoriaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CategoriaEntity crearCategoria(CategoriaEntity categoria) {
		// TODO Auto-generated method stub
		return categoriaDao.save(categoria);
	}

	@Override
	@Transactional
	public CategoriaEntity updateCategoria(Long id, CategoriaEntity newCategoria) {
		// TODO Auto-generated method stub
		CategoriaEntity categoriaExistente = categoriaDao.findById(id).orElse(null);

		if(categoriaExistente != null) {
			if (newCategoria.getNombre() != null) {
				categoriaExistente.setNombre(newCategoria.getNombre());
			}
			if (newCategoria.getEstado()!= null) {
				categoriaExistente.setEstado(newCategoria.getEstado());
			}
			
			return categoriaDao.save(categoriaExistente);
			
		} else {
			return null;
		}
		
		
	}

	@Override
	@Transactional
	public void deleteCategoria(Long id) {
		// TODO Auto-generated method stub
		categoriaDao.deleteById(id);
	}
	

}
