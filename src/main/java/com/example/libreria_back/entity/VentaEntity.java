package com.example.libreria_back.entity;

import java.sql.Date;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Long idVenta;

	@Column(name = "nº_venta")
	private Integer numVenta;

	@Column(name = "cambio")
	private Double cambio;

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

	@Column(name = "fecha_venta")
	private Date fechaVenta;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@NotNull
	private UsuarioEntity usuario;

	@ManyToOne
	@JoinColumn(name = "id_trabajador")
	@NotNull
	private TrabajadorEntity trabajador;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@NotNull
	private ClienteEntity cliente;

	public enum EstadoVenta {
		ACTIVA,
		INACTIVA
	}
}
