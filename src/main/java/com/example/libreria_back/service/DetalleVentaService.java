package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.DetalleVentaEntity;

public interface DetalleVentaService {

	 List<DetalleVentaEntity> listarDetalleVenta();
	    DetalleVentaEntity listarDetalleVentaPorId(Long id);
	    DetalleVentaEntity crearDetalleVenta(DetalleVentaEntity detalleventa);
	    DetalleVentaEntity updateDetalleVenta(Long id, DetalleVentaEntity newDetalleVenta);
	    void deleteDetalleVenta(Long id);
}
