package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.UsuarioEntity;

public interface UsuarioService {

	 List<UsuarioEntity> listarUsuarios();
	    UsuarioEntity listarUsuarioPorId(Long id);
	 
	    UsuarioEntity crearUsuario(UsuarioEntity usuario);
	    UsuarioEntity updateUsuario(Long id, UsuarioEntity newUsuario);
	    void deleteUsuario(Long id);
}
