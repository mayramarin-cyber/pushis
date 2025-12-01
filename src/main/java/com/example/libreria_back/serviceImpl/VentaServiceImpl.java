package com.example.libreria_back.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreria_back.dao.VentaDao;
import com.example.libreria_back.entity.ClienteEntity;
import com.example.libreria_back.entity.TrabajadorEntity;
import com.example.libreria_back.entity.UsuarioEntity;
import com.example.libreria_back.entity.VentaEntity;
import com.example.libreria_back.service.ClienteService;
import com.example.libreria_back.service.TrabajadorService;
import com.example.libreria_back.service.UsuarioService;
import com.example.libreria_back.service.VentaService;

import jakarta.transaction.Transactional;
@Service
public class VentaServiceImpl implements VentaService{

	private final VentaDao ventaDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	public VentaServiceImpl(VentaDao ventaDao) {
		this.ventaDao = ventaDao;
	}
	
	@Override
	@Transactional
	public List<VentaEntity> listarVentas() {
		// TODO Auto-generated method stub
		return ventaDao.findAll();
	}

	@Override
	@Transactional
	public VentaEntity listarVentasPorId(Long id) {
		// TODO Auto-generated method stub
		return ventaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public VentaEntity crearVenta(VentaEntity venta) {
		if (venta.getUsuario() != null) {
	        // Asegúrate de que el usuario exista antes de crear la venta
	        UsuarioEntity usuario = usuarioService.listarUsuarioPorId(venta.getUsuario().getIdUsuario());
	        if (usuario != null) {
	            venta.setUsuario(usuario);
	        } else {
	            return null; // El usuario no existe
	        }
	    }

	    if (venta.getTrabajador() != null) {
	        // Asegúrate de que el trabajador exista antes de crear la venta
	        TrabajadorEntity trabajador = trabajadorService.listarTrabajadorPorId(venta.getTrabajador().getIdTrabajador());
	        if (trabajador != null) {
	            venta.setTrabajador(trabajador);
	        } else {
	            return null; // El trabajador no existe
	        }
	    }

	    if (venta.getCliente() != null) {
	        // Asegúrate de que el cliente exista antes de crear la venta
	        ClienteEntity cliente = clienteService.listarClientePorId(venta.getCliente().getIdCliente());
	        if (cliente != null) {
	            venta.setCliente(cliente);
	        } else {
	            return null; // El cliente no existe
	        }
	    }

	    return ventaDao.save(venta);
	}

	@Override
	@Transactional
	public VentaEntity updateVenta(Long id, VentaEntity newVenta) {
		 try {
		        VentaEntity ventaExistente = ventaDao.findById(id).orElse(null);

		        if (ventaExistente != null) {
		            // Actualiza los campos básicos de la venta
		        	
		        	
		        	 if (newVenta.getNumVenta() != null) {
			                ventaExistente.setNumVenta(newVenta.getNumVenta());
			            }
		        	
		        	 if (newVenta.getCambio() != null) {
			                ventaExistente.setCambio(newVenta.getCambio());
			            }
		        	
		            if (newVenta.getRuc() != null) {
		                ventaExistente.setRuc(newVenta.getRuc());
		            }
		            if (newVenta.getEstado() != null) {
		                ventaExistente.setEstado(newVenta.getEstado());
		            }
		            if (newVenta.getMoneda() != null) {
		                ventaExistente.setMoneda(newVenta.getMoneda());
		            }
		            if (newVenta.getTotalVenta() != null) {
		                ventaExistente.setTotalVenta(newVenta.getTotalVenta());
		            }
		            if (newVenta.getMetodoPago() != null) {
		                ventaExistente.setMetodoPago(newVenta.getMetodoPago());
		            }
		            if (newVenta.getFecnaVenta() != null) {
		                ventaExistente.setFecnaVenta(newVenta.getFecnaVenta());
		            }

		            // Actualiza las relaciones con las entidades relacionadas (usuario, trabajador, cliente)
		            if (newVenta.getUsuario() != null) {
		                if (newVenta.getUsuario().getIdUsuario() != null) {
		                    UsuarioEntity usuario = usuarioService.listarUsuarioPorId(newVenta.getUsuario().getIdUsuario());
		                    ventaExistente.setUsuario(usuario);
		                } else {
		                    ventaExistente.setUsuario(null);
		                }
		            }

		            if (newVenta.getTrabajador() != null) {
		                if (newVenta.getTrabajador().getIdTrabajador() != null) {
		                    TrabajadorEntity trabajador = trabajadorService.listarTrabajadorPorId(newVenta.getTrabajador().getIdTrabajador());
		                    ventaExistente.setTrabajador(trabajador);
		                } else {
		                    ventaExistente.setTrabajador(null);
		                }
		            }

		            if (newVenta.getCliente() != null) {
		                if (newVenta.getCliente().getIdCliente() != null) {
		                    ClienteEntity cliente = clienteService.listarClientePorId(newVenta.getCliente().getIdCliente());
		                    ventaExistente.setCliente(cliente);
		                } else {
		                    ventaExistente.setCliente(null);
		                }
		            }

		            return ventaDao.save(ventaExistente);
		        } else {
		            return null; // No se encontró la venta a actualizar
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // Manejo de excepciones personalizado
		        return null;
		    }
	}

	@Override
	@Transactional
	public void deleteVenta(Long id) {
		ventaDao.deleteById(id);
		
	}
	
	

}
