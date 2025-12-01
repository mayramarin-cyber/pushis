package com.example.libreria_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalleventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
