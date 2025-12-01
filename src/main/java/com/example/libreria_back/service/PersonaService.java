package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.PersonaEntity;

public interface PersonaService {

	List<PersonaEntity> listarPersonas();

	PersonaEntity listarPersonaPorId(Long id);

	PersonaEntity crearPersona(PersonaEntity persona);

	PersonaEntity updatePersona(Long id, PersonaEntity newPersona);

	void deletePersona(Long id);
}
