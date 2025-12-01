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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "usuarios")
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
	private String imgPerfil; // Cambia el tipo de dato según el tipo de imagen que quieras almacenar

	@ManyToOne
	@JoinColumn(name = "id_rol")
	@NotNull
	private RolEntity rol; // Asume que tienes una entidad "RolEntity" para representar el rol

	@ManyToOne
	@JoinColumn(name = "id_trabajadores")
	@NotNull
	private TrabajadorEntity trabajador; // Asume que tienes una entidad "TrabajadorEntity" para representar el
											// trabajador

	public UsuarioEntity() {
		this.rol = new RolEntity();
		this.trabajador = new TrabajadorEntity();
	}

	

	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public EstadoUsuario getEstado() {
		return estado;
	}



	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}



	public String getImgPerfil() {
		return imgPerfil;
	}



	public void setImgPerfil(String imgPerfil) {
		this.imgPerfil = imgPerfil;
	}



	public RolEntity getRol() {
		return rol;
	}



	public void setRol(RolEntity rol) {
		this.rol = rol;
	}



	public TrabajadorEntity getTrabajador() {
		return trabajador;
	}



	public void setTrabajador(TrabajadorEntity trabajador) {
		this.trabajador = trabajador;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UsuarioEntity that = (UsuarioEntity) o;
		return Objects.equals(idUsuario, that.idUsuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}
	
	

    public enum EstadoUsuario {
        ACTIVO,
        INACTIVO
    }
}
