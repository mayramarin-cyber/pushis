package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.EditorialEntity;



public interface EditorialService {
    
	List<EditorialEntity>listarEditoriales();
	EditorialEntity listarEditorialPorId(long id);
	EditorialEntity crearEditorial(EditorialEntity editorial);
	EditorialEntity updateEditorial(Long id, EditorialEntity newEditorial);
	void deleteEditorial(Long id);
}
