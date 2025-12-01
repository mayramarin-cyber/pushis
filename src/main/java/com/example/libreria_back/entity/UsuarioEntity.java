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
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EstadoUsuario estado;

	@Column(name = "img_perfil")
	private String imgPerfil;

	@ManyToOne
	@JoinColumn(name = "id_rol")
	@NotNull
	private RolEntity rol;

	@ManyToOne
	@JoinColumn(name = "id_trabajadores")
	@NotNull
	private TrabajadorEntity trabajador;

	public enum EstadoUsuario {
		ACTIVO,
		INACTIVO
	}
}
