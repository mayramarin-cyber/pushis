package com.example.libreria_back.service;

import java.util.List;

import com.example.libreria_back.entity.VentaEntity;

public interface VentaService {
	
	 List<VentaEntity> listarVentas();
	    VentaEntity listarVentasPorId(Long id);
	 
	    VentaEntity crearVenta(VentaEntity venta);
	    VentaEntity updateVenta(Long id, VentaEntity newVenta);
	    void deleteVenta(Long id);

}
