package com.example.libreria_back.entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "ventas")
public class VentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Long idVenta;

	@Column(name = "nº_venta")
	private Integer numVenta;

	@Column(name = "cambio")
	private Integer cambio;

	@Column(name = "ruc")
	private String ruc;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EstadoVenta estado;

	@Column(name = "moneda")
	private String moneda;

	@Column(name = "total_venta")
	private Double totalVenta;

	@Column(name = "metodo_pago")
	private String metodoPago;

	@Column(name = "fecna_venta")
	private Date fecnaVenta;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@NotNull
	private UsuarioEntity usuario; // Asume que tienes una entidad "UsuarioEntity" para representar el usuario

	@ManyToOne
	@JoinColumn(name = "id_trabajador")
	@NotNull
	private TrabajadorEntity trabajador; // Asume que tienes una entidad "TrabajadorEntity" para representar el
											// trabajador

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@NotNull
	private ClienteEntity cliente; // Asume que tienes una entidad "ClienteEntity" para representar el cliente

	public VentaEntity() {
		this.usuario = new UsuarioEntity();
		this.trabajador = new TrabajadorEntity();
		this.cliente = new ClienteEntity();
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getNumVenta() {
		return numVenta;
	}

	public void setNumVenta(Integer numVenta) {
		this.numVenta = numVenta;
	}

	public Integer getCambio() {
		return cambio;
	}

	public void setCambio(Integer cambio) {
		this.cambio = cambio;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public EstadoVenta getEstado() {
		return estado;
	}

	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Date getFecnaVenta() {
		return fecnaVenta;
	}

	public void setFecnaVenta(Date fecnaVenta) {
		this.fecnaVenta = fecnaVenta;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public TrabajadorEntity getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(TrabajadorEntity trabajador) {
		this.trabajador = trabajador;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VentaEntity that = (VentaEntity) o;
		return Objects.equals(idVenta, that.idVenta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVenta);
	}

	public enum EstadoVenta {
		ACTIVA, INACTIVA
	}
}
