package com.example.libreria_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria_back.dao.ClienteDao;
import com.example.libreria_back.entity.ClienteEntity;
import com.example.libreria_back.entity.PersonaEntity;
import com.example.libreria_back.service.ClienteService;
import com.example.libreria_back.service.PersonaService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ClienteDao clienteDao ;

	@GetMapping("/listar")
	public ResponseEntity<List<ClienteEntity>> listarCliente() {
		List<ClienteEntity> cliente = clienteService.listarClientes();
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteEntity> listarClientePorId(@PathVariable Long id) {
		  ClienteEntity cliente = clienteDao.findByIdWithPersona(id).orElse(null);

		    if (cliente != null) {
		        return new ResponseEntity<>(cliente, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	}

	
	@GetMapping("/buscarPorDNI/{dni}")
	public List<ClienteEntity> buscarPersonaPorDni(@PathVariable Integer dni) {
        return clienteService.buscarPersonaPorDni(dni);
    }
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteEntity cliente) {

		try {
			if (cliente.getPersona() == null || cliente.getPersona().getIdPersona() == null) {
				return new ResponseEntity<>("La persona no es válida o está ausente.", HttpStatus.BAD_REQUEST);
			}

			PersonaEntity persona = personaService.listarPersonaPorId(cliente.getPersona().getIdPersona());
			if (persona == null) {
				return new ResponseEntity<>("La persona proporcionada no es válida.", HttpStatus.BAD_REQUEST);
			}
			cliente.setPersona(persona);

			ClienteEntity newCliente = clienteService.crearCliente(cliente);

			return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear el cliente: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteEntity newCliente) {
		try {

			if (newCliente.getPersona() != null && newCliente.getPersona().getIdPersona() != null) {
				PersonaEntity persona = personaService.listarPersonaPorId(newCliente.getPersona().getIdPersona());
				if (persona == null) {
					return new ResponseEntity<>("La persona proporcionado no es válido.", HttpStatus.BAD_REQUEST);
				}
				newCliente.setPersona(persona);
			} else {
				newCliente.setPersona(null);
			}

			ClienteEntity clienteActualizado = clienteService.updateCliente(id, newCliente);
			if (clienteActualizado != null) {
				return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al actualizar el cliente: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		clienteService.deleteCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
