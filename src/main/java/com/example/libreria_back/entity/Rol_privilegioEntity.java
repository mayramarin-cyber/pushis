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
@Table(name = "rol_privilegio")
public class Rol_privilegioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol_privilegio")
	private Long idRol_privilegio;
	

@ManyToOne
	@JoinColumn(name = "id_rol")
	@NotNull
	private RolEntity rol;

	@ManyToOne
	@JoinColumn(name = "id_privilegio")
	@NotNull
	private PrivilegioEntity privilegio;

	public Rol_privilegioEntity() {
	    this.rol = new RolEntity();
	    this.privilegio = new PrivilegioEntity();
	 
	  }

	public Long getIdRol_privilegio() {
		return idRol_privilegio;
	}

	public void setIdRol_privilegio(Long idRol_privilegio) {
		this.idRol_privilegio = idRol_privilegio;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(RolEntity rol) {
		this.rol = rol;
	}

	public PrivilegioEntity getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(PrivilegioEntity privilegio) {
		this.privilegio = privilegio;
	}

	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Rol_privilegioEntity that = (Rol_privilegioEntity) o;
		return Objects.equals(idRol_privilegio, that.idRol_privilegio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRol_privilegio);
	}

}
