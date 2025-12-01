package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.PersonaDao;
import com.example.libreria_back.entity.PersonaEntity;
import com.example.libreria_back.service.PersonaService;

import jakarta.transaction.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

	private final PersonaDao personaDao;

	@Autowired

	public PersonaServiceImpl(PersonaDao personaDao) {
		this.personaDao = personaDao;
	}

	@Override
	@Transactional
	public List<PersonaEntity> listarPersonas() {
		// TODO Auto-generated method stub
		return personaDao.findAll();
	}

	@Override
	@Transactional
	public PersonaEntity listarPersonaPorId(Long id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PersonaEntity crearPersona(PersonaEntity persona) {
		// TODO Auto-generated method stub
		return personaDao.save(persona);
	}

	@Override
	@Transactional
	public PersonaEntity updatePersona(Long id, PersonaEntity newPersona) {
		PersonaEntity personaExistente = personaDao.findById(id).orElse(null);

		if (personaExistente != null) {
			if (newPersona.getNombre() != null) {
				personaExistente.setNombre(newPersona.getNombre());
			}
			if (newPersona.getApellidos() != null) {
				personaExistente.setApellidos(newPersona.getApellidos());
			}

			if (newPersona.getDni() != null) {
				personaExistente.setDni(newPersona.getDni());
			}

			if (newPersona.getTelefono() != null) {
				personaExistente.setTelefono(newPersona.getTelefono());
			}
			return personaDao.save(personaExistente);

		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void deletePersona(Long id) {
		personaDao.deleteById(id);

	}

}
