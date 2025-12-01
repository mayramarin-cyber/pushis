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

import com.example.libreria_back.entity.DetalleVentaEntity;
import com.example.libreria_back.entity.LibroEntity;
import com.example.libreria_back.entity.VentaEntity;
import com.example.libreria_back.service.DetalleVentaService;
import com.example.libreria_back.service.LibroService;
import com.example.libreria_back.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalleventas")
public class DetalleVentaController {

	@Autowired
	private DetalleVentaService detalleVentaService;

	@Autowired
	private VentaService ventaService;

	@Autowired
	private LibroService libroService;

	@GetMapping("/listar")
	public ResponseEntity<List<DetalleVentaEntity>> listarDetalleVentas() {
		List<DetalleVentaEntity> detalleVentas = detalleVentaService.listarDetalleVenta();
		return new ResponseEntity<>(detalleVentas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalleVentaEntity> listarDetalleVentaPorId(@PathVariable Long id) {
		DetalleVentaEntity detalleVenta = detalleVentaService.listarDetalleVentaPorId(id);
		if (detalleVenta != null) {
			return new ResponseEntity<>(detalleVenta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<?> crearDetalleVenta(@RequestBody DetalleVentaEntity detalleVenta) {
		try {
			if (detalleVenta.getVenta() != null && detalleVenta.getVenta().getIdVenta() != null) {
				VentaEntity venta = ventaService.listarVentasPorId(detalleVenta.getVenta().getIdVenta());
				if (venta != null) {
					detalleVenta.setVenta(venta);
				} else {
					return new ResponseEntity<>("La venta proporcionada no es válida.", HttpStatus.BAD_REQUEST);
				}
			} else {
				detalleVenta.setVenta(null);
			}

			if (detalleVenta.getLibro() != null && detalleVenta.getLibro().getIdLibro() != null) {
				LibroEntity libro = libroService.listarLibroPorId(detalleVenta.getLibro().getIdLibro());
				if (libro != null) {
					detalleVenta.setLibro(libro);
				} else {
					return new ResponseEntity<>("El libro proporcionado no es válido.", HttpStatus.BAD_REQUEST);
				}
			} else {
				detalleVenta.setLibro(null);
			}

			DetalleVentaEntity nuevoDetalleVenta = detalleVentaService.crearDetalleVenta(detalleVenta);

			return new ResponseEntity<>(nuevoDetalleVenta, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear el detalle de venta: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDetalleVenta(@PathVariable Long id,
			@RequestBody DetalleVentaEntity newDetalleVenta) {
		try {
			DetalleVentaEntity detalleVentaExistente = detalleVentaService.listarDetalleVentaPorId(id);

			if (detalleVentaExistente != null) {
				// Actualiza los campos básicos del detalle de venta
				if (newDetalleVenta.getCantidad() != null) {
					detalleVentaExistente.setCantidad(newDetalleVenta.getCantidad());
				}
				if (newDetalleVenta.getPrecioCompra() != null) {
					detalleVentaExistente.setPrecioCompra(newDetalleVenta.getPrecioCompra());
				}

				// Actualiza los campos relacionados: venta y libro
				if (newDetalleVenta.getVenta() != null) {
					if (newDetalleVenta.getVenta().getIdVenta() != null) {
						VentaEntity venta = ventaService.listarVentasPorId(newDetalleVenta.getVenta().getIdVenta());
						detalleVentaExistente.setVenta(venta);
					} else {
						detalleVentaExistente.setVenta(null);
					}
				}

				if (newDetalleVenta.getLibro() != null) {
					if (newDetalleVenta.getLibro().getIdLibro() != null) {
						LibroEntity libro = libroService.listarLibroPorId(newDetalleVenta.getLibro().getIdLibro());
						detalleVentaExistente.setLibro(libro);
					} else {
						detalleVentaExistente.setLibro(null);
					}
				}

				DetalleVentaEntity detalleVentaActualizado = detalleVentaService.updateDetalleVenta(id,
						detalleVentaExistente);
				return new ResponseEntity<>(detalleVentaActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Detalle de venta no encontrado.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al actualizar el detalle de venta: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteDetalleVenta(@PathVariable Long id) {
		detalleVentaService.deleteDetalleVenta(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
