package com.example.libreria_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Long idPersona;

	@Column(name = "nombre")
	@NotBlank(message = "El campo 'nombre' no puede estar en blanco")
	private String nombre;

	@Column(name = "apellidos")
	@NotBlank(message = "El campo 'apellidos' no puede estar en blanco")
	private String apellidos;

	@Column(name = "dni")
	private String dni;

	@Column(name = "telefono")
	private String telefono;

}
