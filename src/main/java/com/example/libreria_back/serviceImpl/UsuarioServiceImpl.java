package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.UsuarioDao;
import com.example.libreria_back.entity.RolEntity;
import com.example.libreria_back.entity.TrabajadorEntity;
import com.example.libreria_back.entity.UsuarioEntity;
import com.example.libreria_back.service.RolService;
import com.example.libreria_back.service.TrabajadorService;
import com.example.libreria_back.service.UsuarioService;

import jakarta.transaction.Transactional;
@Service
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioDao usuarioDao;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	

	@Override
	@Transactional
	public List<UsuarioEntity> listarUsuarios() {
		// TODO Auto-generated method stub
		return usuarioDao.findAll();
	}

	@Override
	@Transactional
	public UsuarioEntity listarUsuarioPorId(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
		 if (usuario.getRol() != null) {
	            // Asegúrate de que el rol exista antes de crear el usuario
	            RolEntity rol = rolService.listarRolesPorId(usuario.getRol().getIdRol());
	            if (rol != null) {
	                usuario.setRol(rol);
	            } else {
	                return null; // El rol no existe
	            }
	        }

	        if (usuario.getTrabajador() != null) {
	            // Asegúrate de que el trabajador exista antes de crear el usuario
	            TrabajadorEntity trabajador = trabajadorService.listarTrabajadorPorId(usuario.getTrabajador().getIdTrabajador());
	            if (trabajador != null) {
	                usuario.setTrabajador(trabajador);
	            } else {
	                return null; // El trabajador no existe
	            }
	        }

	        return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public UsuarioEntity updateUsuario(Long id, UsuarioEntity newUsuario) {
		 try {
	            UsuarioEntity usuarioExistente = usuarioDao.findById(id).orElse(null);

	            if (usuarioExistente != null) {
	                // Actualiza los campos básicos del usuario
	                if (newUsuario.getEstado() != null) {
	                    usuarioExistente.setEstado(newUsuario.getEstado());
	                }

	                // Actualiza los campos relacionados: rol y trabajador
	                if (newUsuario.getRol() != null) {
	                    if (newUsuario.getRol().getIdRol() != null) {
	                        RolEntity rol = rolService.listarRolesPorId(newUsuario.getRol().getIdRol());
	                        usuarioExistente.setRol(rol);
	                    } else {
	                        usuarioExistente.setRol(null);
	                    }
	                }

	                if (newUsuario.getTrabajador() != null) {
	                    if (newUsuario.getTrabajador().getIdTrabajador() != null) {
	                        TrabajadorEntity trabajador = trabajadorService.listarTrabajadorPorId(newUsuario.getTrabajador().getIdTrabajador());
	                        usuarioExistente.setTrabajador(trabajador);
	                    } else {
	                        usuarioExistente.setTrabajador(null);
	                    }
	                }

	                // Actualiza otros campos según sea necesario
	                if (newUsuario.getEmail() != null) {
	                    usuarioExistente.setEmail(newUsuario.getEmail());
	                }

	                if (newUsuario.getPassword() != null) {
	                    usuarioExistente.setPassword(newUsuario.getPassword());
	                }

	                if (newUsuario.getImgPerfil() != null) {
	                    usuarioExistente.setImgPerfil(newUsuario.getImgPerfil());
	                }

	                return usuarioDao.save(usuarioExistente);
	            } else {
	                return null; // No se encontró el usuario a actualizar
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Manejo de excepciones personalizado
	            return null;
	        }
	}

	@Override
	@Transactional
	public void deleteUsuario(Long id) {
		 usuarioDao.deleteById(id);
		
	}

}
