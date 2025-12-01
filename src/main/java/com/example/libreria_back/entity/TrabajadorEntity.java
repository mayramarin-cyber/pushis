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
@Table(name = "trabajadores")
public class TrabajadorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trabajador")
	private Long idTrabajador;
	
	 @Column(name="codigo")
	  private String codigo;

	  @Column(name="estado_laboral")
	  private Boolean estadoLaboral;

	  @Column(name="estado")
	  @Enumerated(EnumType.STRING)
	  private EstadoTrabajador estado;
	  
	  @ManyToOne
	  @JoinColumn(name="id_persona")
	  @NotNull
	  private PersonaEntity persona;

	  public TrabajadorEntity() {
	    this.persona = new PersonaEntity();
	  }
	  
	  public enum EstadoTrabajador {
		  ACTIVO,
		  INACTIVO
		}

	public Long getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(Long idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getEstadoLaboral() {
		return estadoLaboral;
	}

	public void setEstadoLaboral(Boolean estadoLaboral) {
		this.estadoLaboral = estadoLaboral;
	}

	public EstadoTrabajador getEstado() {
		return estado;
	}

	public void setEstado(EstadoTrabajador estado) {
		this.estado = estado;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(PersonaEntity persona) {
		this.persona = persona;
	}
    

@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TrabajadorEntity that = (TrabajadorEntity) o;
		return Objects.equals(idTrabajador, that.idTrabajador);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTrabajador);
	}

	  
	  
}
