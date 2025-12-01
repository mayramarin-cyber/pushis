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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabajadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trabajador")
	private Long idTrabajador;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "estado_laboral")
	private Boolean estadoLaboral;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EstadoTrabajador estado;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	@NotNull
	private PersonaEntity persona;

	public enum EstadoTrabajador {
		ACTIVO,
		INACTIVO
	}
}
