package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.ClienteDao;
import com.example.libreria_back.entity.ClienteEntity;
import com.example.libreria_back.entity.PersonaEntity;
import com.example.libreria_back.service.ClienteService;
import com.example.libreria_back.service.PersonaService;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService{
 
	private final ClienteDao clienteDao;

	@Autowired
	
	private PersonaService personaService;

	public ClienteServiceImpl( ClienteDao clienteDao) {
	this.clienteDao = clienteDao;
}
	
	@Override
	@Transactional
	public List<ClienteEntity> listarClientes() {
		// TODO Auto-generated method stub
		return clienteDao.findAll();
	}

	@Override
	@Transactional
	public ClienteEntity listarClientePorId(long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}
	
	
	@Override
	@Transactional
	public List<ClienteEntity> buscarPersonaPorDni(Integer dni) {
		   System.out.println("Buscando clientes con DNI: " + dni);
		    List<ClienteEntity> clientes = clienteDao.findByPersonaDni(dni);
		    System.out.println("Clientes encontrados: " + clientes);
		    return clientes;
	}

	@Override
	@Transactional
	public ClienteEntity crearCliente(ClienteEntity cliente) {
		
		if (cliente.getPersona() != null) {
			PersonaEntity persona = personaService.listarPersonaPorId(cliente.getPersona().getIdPersona());
			cliente.setPersona(persona);
		}

	    return clienteDao.save(cliente);

	  }

	@Override
	@Transactional
	public ClienteEntity updateCliente(Long id, ClienteEntity newCliente) {
		try {
			// Verifica si el cliente que se intenta actualizar existe en la base de datos
			ClienteEntity clienteExistente = clienteDao.findById(id).orElse(null);

			if (clienteExistente != null) {
				// Actualiza los campos básicos del cliente
		
				if (newCliente.getEstado() != null) {
					clienteExistente.setEstado(newCliente.getEstado());
				}

				// Actualiza los campos relacionados: persona
				
				
				if (newCliente.getPersona() != null && newCliente.getPersona().getIdPersona() != null) {
					PersonaEntity persona = personaService
							.listarPersonaPorId(newCliente.getPersona().getIdPersona());
					clienteExistente.setPersona(persona);
				}

				// Guarda el libro actualizado en la base de datos
				return clienteDao.save(clienteExistente);
			} else {
				return null; // No se encontró el cliente a actualizar
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
	public void deleteCliente(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

}
