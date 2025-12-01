package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.ClienteEntity;

public interface ClienteService {

	List<ClienteEntity> listarClientes();

	ClienteEntity listarClientePorId(long id);

	List<ClienteEntity> buscarPersonaPorDni(String dni);

	ClienteEntity crearCliente(ClienteEntity cliente);

	ClienteEntity updateCliente(Long id, ClienteEntity newCliente);

	void deleteCliente(Long id);

}
