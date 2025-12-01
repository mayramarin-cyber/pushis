package com.example.libreria_back.entity;

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

@Entity
@Table(name = "detalleventas")
public class DetalleVentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_venta")
	private Long idDetalleVenta;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "preciocompra")
	private Double precioCompra;

	@ManyToOne
	@JoinColumn(name = "id_venta")
	@NotNull
	private VentaEntity venta;

	@ManyToOne
	@JoinColumn(name = "id_libro")
	@NotNull
	private LibroEntity libro;

	public DetalleVentaEntity() {
		this.venta = new VentaEntity();
		this.libro = new LibroEntity();

	}

	public Long getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(Long idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public VentaEntity getVenta() {
		return venta;
	}

	public void setVenta(VentaEntity venta) {
		this.venta = venta;
	}

	public LibroEntity getLibro() {
		return libro;
	}

	public void setLibro(LibroEntity libro) {
		this.libro = libro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DetalleVentaEntity that = (DetalleVentaEntity) o;
		return Objects.equals(idDetalleVenta, that.idDetalleVenta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDetalleVenta);
	}
}
