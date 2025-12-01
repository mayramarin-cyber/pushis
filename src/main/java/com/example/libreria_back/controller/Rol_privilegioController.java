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
import com.example.libreria_back.entity.RolEntity;
import com.example.libreria_back.entity.Rol_privilegioEntity;
import com.example.libreria_back.service.PrivilegioService;
import com.example.libreria_back.service.RolService;
import com.example.libreria_back.service.Rol_privilegioService;
import java.util.List;

@RestController
@RequestMapping("/api/rol_privilegios")
public class Rol_privilegioController {

	@Autowired
	private Rol_privilegioService rol_privilegioService;

	@Autowired
	private RolService rolService;

	@Autowired
	private PrivilegioService privilegioService;

	@GetMapping("/listar")
	public ResponseEntity<List<Rol_privilegioEntity>> listarRol_privilegio() {
		List<Rol_privilegioEntity> rol_privilegio = rol_privilegioService.listarRol_privilegio();
		return new ResponseEntity<>(rol_privilegio, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rol_privilegioEntity> listarRol_privilegioPorId(@PathVariable Long id) {
		Rol_privilegioEntity rol_privilegio = rol_privilegioService.listarRol_privilegioPorId(id);
		if (rol_privilegio != null) {
			return new ResponseEntity<>(rol_privilegio, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<?> crearRol_privilegio(@RequestBody Rol_privilegioEntity rol_privilegio) {
		try {
			if (rol_privilegio.getRol() == null || rol_privilegio.getRol().getIdRol() == null) {
				return new ResponseEntity<>("La categoría no es válida o está ausente.", HttpStatus.BAD_REQUEST);
			}

			RolEntity rol = rolService.listarRolesPorId(rol_privilegio.getRol().getIdRol());
			if (rol == null) {
				return new ResponseEntity<>("La categoría proporcionada no es válida.", HttpStatus.BAD_REQUEST);
			}
			rol_privilegio.setRol(rol);

			if (rol_privilegio.getPrivilegio() != null && rol_privilegio.getPrivilegio().getIdPrivilegio() != null) {
				PrivilegioEntity privilegio = privilegioService
						.listarPrivilegioPorId(rol_privilegio.getPrivilegio().getIdPrivilegio());
				if (privilegio == null) {
					return new ResponseEntity<>("La editorial proporcionada no es válida.", HttpStatus.BAD_REQUEST);
				}
				rol_privilegio.setPrivilegio(privilegio);
			} else {
				rol_privilegio.setPrivilegio(null);
			}

			Rol_privilegioEntity nuevoRol_privilegio = rol_privilegioService.crearRol_privilegio(rol_privilegio);

			return new ResponseEntity<>(nuevoRol_privilegio, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear el Rol_privilegio: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRol_privilegio(@PathVariable Long id, @RequestBody Rol_privilegioEntity newRol_privilegio) {
		try {

			if (newRol_privilegio.getRol() != null && newRol_privilegio.getRol().getIdRol() != null) {
				RolEntity rol = rolService.listarRolesPorId(newRol_privilegio.getRol().getIdRol());
				if (rol == null) {
					return new ResponseEntity<>("El autor proporcionado no es válido.", HttpStatus.BAD_REQUEST);
				}
				newRol_privilegio.setRol(rol);
			} else {
				newRol_privilegio.setRol(null);
			}

			if (newRol_privilegio.getPrivilegio() != null && newRol_privilegio.getPrivilegio().getIdPrivilegio() != null) {
				PrivilegioEntity privilegio = privilegioService.listarPrivilegioPorId(newRol_privilegio.getPrivilegio().getIdPrivilegio());
				if (privilegio == null) {
					return new ResponseEntity<>("El privilegio proporcionada no es válida.", HttpStatus.BAD_REQUEST);
				}
				newRol_privilegio.setPrivilegio(privilegio);
			} else {
				newRol_privilegio.setPrivilegio(null);
			}

			

			Rol_privilegioEntity rol_privilegioActualizado = rol_privilegioService.updateRol_privilegio(id, newRol_privilegio);
			if (rol_privilegioActualizado != null) {
				return new ResponseEntity<>(rol_privilegioActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al actualizar el Rol_privilegio: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteRol_privilegio(@PathVariable Long id) {
		rol_privilegioService.deleteRol_privilegio(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
