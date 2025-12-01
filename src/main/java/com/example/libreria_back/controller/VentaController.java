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

import com.example.libreria_back.entity.ClienteEntity;
import com.example.libreria_back.entity.TrabajadorEntity;
import com.example.libreria_back.entity.UsuarioEntity;
import com.example.libreria_back.entity.VentaEntity;
import com.example.libreria_back.service.ClienteService;
import com.example.libreria_back.service.TrabajadorService;
import com.example.libreria_back.service.UsuarioService;
import com.example.libreria_back.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<VentaEntity>> listarVentas() {
        List<VentaEntity> ventas = ventaService.listarVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaEntity> listarVentaPorId(@PathVariable Long id) {
        VentaEntity venta = ventaService.listarVentasPorId(id);
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearVenta(@RequestBody VentaEntity venta) {
        try {
            if (venta.getUsuario() != null) {
                // Asegúrate de que el usuario exista antes de crear la venta
                UsuarioEntity usuario = usuarioService.listarUsuarioPorId(venta.getUsuario().getIdUsuario());
                if (usuario != null) {
                    venta.setUsuario(usuario);
                } else {
                    return new ResponseEntity<>("El usuario no es válido o está ausente.", HttpStatus.BAD_REQUEST);
                }
            }

            if (venta.getTrabajador() != null) {
                // Asegúrate de que el trabajador exista antes de crear la venta
                TrabajadorEntity trabajador = trabajadorService
                        .listarTrabajadorPorId(venta.getTrabajador().getIdTrabajador());
                if (trabajador != null) {
                    venta.setTrabajador(trabajador);
                } else {
                    return new ResponseEntity<>("El trabajador no es válido o está ausente.", HttpStatus.BAD_REQUEST);
                }
            }

            if (venta.getCliente() != null) {
                // Asegúrate de que el cliente exista antes de crear la venta
                ClienteEntity cliente = clienteService.listarClientePorId(venta.getCliente().getIdCliente());
                if (cliente != null) {
                    venta.setCliente(cliente);
                } else {
                    return new ResponseEntity<>("El cliente no es válido o está ausente.", HttpStatus.BAD_REQUEST);
                }
            }

            VentaEntity nuevaVenta = ventaService.crearVenta(venta);

            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al crear la venta: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Long id, @RequestBody VentaEntity newVenta) {
        try {
            VentaEntity ventaExistente = ventaService.listarVentasPorId(id);

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
                if (newVenta.getFechaVenta() != null) {
                    ventaExistente.setFechaVenta(newVenta.getFechaVenta());
                }

                // Actualiza las relaciones con las entidades relacionadas (usuario, trabajador,
                // cliente)
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
                        TrabajadorEntity trabajador = trabajadorService
                                .listarTrabajadorPorId(newVenta.getTrabajador().getIdTrabajador());
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

                VentaEntity ventaActualizada = ventaService.updateVenta(id, ventaExistente);
                return new ResponseEntity<>(ventaActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Venta no encontrada.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al actualizar la venta: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
