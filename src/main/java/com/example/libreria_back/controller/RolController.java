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
import com.example.libreria_back.service.RolService;
import java.util.List;
@RestController
@RequestMapping("/api/roles")
public class RolController {

	@Autowired
	private RolService rolService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<RolEntity>> listarRoles() {
		List<RolEntity> roles = rolService.listarRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);

	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<RolEntity> listarRolPorId(@PathVariable Long id) {
		RolEntity rol = rolService.listarRolesPorId(id);
		if (rol != null) {
			return new ResponseEntity<>(rol, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<RolEntity> crearRol(@RequestBody RolEntity rol) {
		RolEntity newRol = rolService.crearRol(rol);
		return new ResponseEntity<>(newRol, HttpStatus.CREATED);

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<RolEntity> updateRol(@PathVariable Long id, @RequestBody RolEntity newRol) {
		RolEntity rolActualizado = rolService.updateRol(id, newRol);
		if (rolActualizado != null) {
			return new ResponseEntity<>(rolActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteEliminar(@PathVariable Long id) {
		rolService.deleteRol(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
