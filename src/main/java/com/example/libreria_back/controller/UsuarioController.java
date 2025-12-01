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

import com.example.libreria_back.entity.RolEntity;
import com.example.libreria_back.entity.TrabajadorEntity;
import com.example.libreria_back.entity.UsuarioEntity;
import com.example.libreria_back.service.RolService;
import com.example.libreria_back.service.TrabajadorService;
import com.example.libreria_back.service.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> listarUsuarioPorId(@PathVariable Long id) {
        UsuarioEntity usuario = usuarioService.listarUsuarioPorId(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioEntity usuario) {
        try {
            if (usuario.getRol() != null) {
                // Asegúrate de que el rol exista antes de crear el usuario
                RolEntity rol = rolService.listarRolesPorId(usuario.getRol().getIdRol());
                if (rol != null) {
                    usuario.setRol(rol);
                } else {
                    return new ResponseEntity<>("El rol no es válido o está ausente.", HttpStatus.BAD_REQUEST);
                }
            }

            if (usuario.getTrabajador() != null) {
                // Asegúrate de que el trabajador exista antes de crear el usuario
                TrabajadorEntity trabajador = trabajadorService.listarTrabajadorPorId(usuario.getTrabajador().getIdTrabajador());
                if (trabajador != null) {
                    usuario.setTrabajador(trabajador);
                } else {
                    return new ResponseEntity<>("El trabajador no es válido o está ausente.", HttpStatus.BAD_REQUEST);
                }
            }

            UsuarioEntity nuevoUsuario = usuarioService.crearUsuario(usuario);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al crear el usuario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody UsuarioEntity newUsuario) {
        try {
            UsuarioEntity usuarioExistente = usuarioService.listarUsuarioPorId(id);

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

                UsuarioEntity usuarioActualizado = usuarioService.updateUsuario(id, usuarioExistente);
                return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al actualizar el usuario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	
}
