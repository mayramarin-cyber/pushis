package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.DetalleVentaDao;
import com.example.libreria_back.entity.DetalleVentaEntity;
import com.example.libreria_back.entity.LibroEntity;
import com.example.libreria_back.entity.VentaEntity;
import com.example.libreria_back.service.DetalleVentaService;
import com.example.libreria_back.service.LibroService;
import com.example.libreria_back.service.VentaService;

import jakarta.transaction.Transactional;
@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

	private final DetalleVentaDao detalleVentaDao;

	@Autowired
	private VentaService ventaService;

	@Autowired
	private LibroService libroService;

	
	public DetalleVentaServiceImpl(DetalleVentaDao detalleVentaDao) {
		this.detalleVentaDao = detalleVentaDao;
	}

	@Override
	@Transactional
	public List<DetalleVentaEntity> listarDetalleVenta() {
		// TODO Auto-generated method stub
		return detalleVentaDao.findAll();
	}

	@Override
	@Transactional
	public DetalleVentaEntity listarDetalleVentaPorId(Long id) {
		// TODO Auto-generated method stub
		return detalleVentaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public DetalleVentaEntity crearDetalleVenta(DetalleVentaEntity detalleventa) {

		if (detalleventa.getVenta() != null) {
			VentaEntity venta = ventaService.listarVentasPorId(detalleventa.getVenta().getIdVenta());
			if (venta != null) {
				detalleventa.setVenta(venta);
			} else {
				return null; // no existe
			}
		}
		if (detalleventa.getLibro() != null) {
			LibroEntity libro = libroService.listarLibroPorId(detalleventa.getLibro().getIdLibro());
			if (libro != null) {
				detalleventa.setLibro(libro);
			} else {
				return null; // no existe
			}
		}
		return detalleVentaDao.save(detalleventa);
	}

	@Override
	@Transactional
	public DetalleVentaEntity updateDetalleVenta(Long id, DetalleVentaEntity newDetalleVenta) {
		try {
			DetalleVentaEntity detalleVentaExistente = detalleVentaDao.findById(id).orElse(null);

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

				return detalleVentaDao.save(detalleVentaExistente);
			} else {
				return null; // No se encontró el detalle de venta a actualizar
			}
		} catch (Exception e) {
			e.printStackTrace(); // Manejo de excepciones personalizado
			return null;
		}

	}

	@Override
	@Transactional
	public void deleteDetalleVenta(Long id) {
		   detalleVentaDao.deleteById(id);

	}

}
