package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.EditorialDao;
import com.example.libreria_back.entity.EditorialEntity;
import com.example.libreria_back.service.EditorialService;

import jakarta.transaction.Transactional;
// xd 
@Service
public class EditorialServiceImpl implements EditorialService{
	
	private final EditorialDao editorialDao;
	
	@Autowired
	public EditorialServiceImpl(EditorialDao editorialDao) {
		this.editorialDao = editorialDao;
	}

	@Override
	@Transactional
	public List<EditorialEntity> listarEditoriales() {
		// TODO Auto-generated method stub
		return editorialDao.findAll();
	}

	@Override
	@Transactional
	public EditorialEntity listarEditorialPorId(long id) {
		// TODO Auto-generated method stub
		return editorialDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EditorialEntity crearEditorial(EditorialEntity editorial) {
		// TODO Auto-generated method stub
		return editorialDao.save(editorial);
	}

	@Override
	@Transactional
	public EditorialEntity updateEditorial(Long id, EditorialEntity newEditorial) {
		// TODO Auto-generated method stub
		EditorialEntity editorialExistente = editorialDao.findById(id).orElse(null);

		if(editorialExistente != null) {
			if (newEditorial.getNombre() != null) {
				editorialExistente.setNombre(newEditorial.getNombre());
			}
			if (newEditorial.getEstado()!= null) {
				editorialExistente.setEstado(newEditorial.getEstado());
			}
			
			return editorialDao.save(editorialExistente);
			
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteEditorial(Long id) {
		// TODO Auto-generated method stub
		editorialDao.deleteById(id);
	}
	
	
}
