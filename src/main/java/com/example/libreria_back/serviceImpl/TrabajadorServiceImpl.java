package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.TrabajadorDao;
import com.example.libreria_back.entity.PersonaEntity;
import com.example.libreria_back.entity.TrabajadorEntity;
import com.example.libreria_back.service.PersonaService;
import com.example.libreria_back.service.TrabajadorService;

import jakarta.transaction.Transactional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService{

	private final TrabajadorDao trabajadorDao;
	
	@Autowired
	private PersonaService personaService;

	@Autowired
	public TrabajadorServiceImpl(TrabajadorDao trabajadorDao) {
		this.trabajadorDao = trabajadorDao;
	}

	
	@Override
	@Transactional
	public List<TrabajadorEntity> listarTrabajadores() {
		// TODO Auto-generated method stub
		return trabajadorDao.findAll();
	}

	@Override
	@Transactional
	public TrabajadorEntity listarTrabajadorPorId(Long id) {
		// TODO Auto-generated method stub
		return trabajadorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public TrabajadorEntity crearTrabajador(TrabajadorEntity trabajador) {
		if (trabajador.getPersona() != null) {
			PersonaEntity persona = personaService.listarPersonaPorId(trabajador.getPersona().getIdPersona());
			trabajador.setPersona(persona);
		}

	    return trabajadorDao.save(trabajador);

	  }
	

	@Override
	@Transactional
	public TrabajadorEntity updateTrabajador(Long id, TrabajadorEntity newTrabajador) {
		try {
            TrabajadorEntity trabajadorExistente = trabajadorDao.findById(id).orElse(null);

            if (trabajadorExistente != null) {
                // Actualiza los campos básicos del trabajador
                if (newTrabajador.getEstado() != null) {
                    trabajadorExistente.setEstado(newTrabajador.getEstado());
                }

                // Actualiza los campos relacionados: persona
                if (newTrabajador.getPersona() != null) {
                    if (newTrabajador.getPersona().getIdPersona() != null) {
                        PersonaEntity persona = personaService.listarPersonaPorId(newTrabajador.getPersona().getIdPersona());
                        trabajadorExistente.setPersona(persona);
                    } else {
                        trabajadorExistente.setPersona(null);
                    }
                }

                // Actualiza otros campos según sea necesario
                if (newTrabajador.getCodigo() != null) {
                    trabajadorExistente.setCodigo(newTrabajador.getCodigo());
                }

                if (newTrabajador.getEstadoLaboral() != null) {
                    trabajadorExistente.setEstadoLaboral(newTrabajador.getEstadoLaboral());
                }

                return trabajadorDao.save(trabajadorExistente);
            } else {
                return null; // No se encontró el trabajador a actualizar
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones personalizado
            return null;
        }
    }

	@Override
	@Transactional
	public void deleteTrabajador(Long id) {
		   trabajadorDao.deleteById(id);
		
	}
	
}
