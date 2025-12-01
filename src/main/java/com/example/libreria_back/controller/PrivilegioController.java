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

import com.example.libreria_back.entity.PrivilegioEntity;
import com.example.libreria_back.service.PrivilegioService;
import java.util.List;

@RestController
@RequestMapping("/api/privilegios")
public class PrivilegioController {
	
	@Autowired
	private PrivilegioService privilegioService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<PrivilegioEntity>> listarPrivilegios() {
		List<PrivilegioEntity> privilegios = privilegioService.listarPrivilegios();
		return new ResponseEntity<>(privilegios, HttpStatus.OK);

	}
	

	@GetMapping("/{id}")
	public ResponseEntity<PrivilegioEntity> listarPrivilegioPorId(@PathVariable Long id) {
		PrivilegioEntity privilegio = privilegioService.listarPrivilegioPorId(id);
		if (privilegio != null) {
			return new ResponseEntity<>(privilegio, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/crear")
	public ResponseEntity<PrivilegioEntity> crearPrivilegio(@RequestBody PrivilegioEntity privilegio) {
		PrivilegioEntity newPrivilegio = privilegioService.crearPrivilegio(privilegio);
		return new ResponseEntity<>(newPrivilegio, HttpStatus.CREATED);

	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PrivilegioEntity> updatePrivilegio(@PathVariable Long id, @RequestBody PrivilegioEntity newPrivilegio) {
		PrivilegioEntity privilegioActualizado = privilegioService.updatePrivilegio(id, newPrivilegio);
		if (privilegioActualizado != null) {
			return new ResponseEntity<>(privilegioActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteEliminar(@PathVariable Long id) {
		privilegioService.deletePrivilegio(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	


}
