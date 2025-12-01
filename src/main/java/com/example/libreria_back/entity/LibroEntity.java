package com.example.libreria_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro")
	private Long idLibro;

	@Column(name = "titulo")
	@NotBlank(message = "El campo 'titulo' no puede estar en blanco")
	private String titulo;

	@Column(name = "codigo")
	@NotBlank(message = "El campo 'codigo' no puede estar en blanco")
	private String codigo;

	@Column(name = "n_paginas")
	private Integer nPaginas;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "stock")
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	@NotNull
	private CategoriaEntity categoria;

	@ManyToOne
	@JoinColumn(name = "id_editorial")
	@NotNull
	private EditorialEntity editorial;

	@ManyToOne
	@JoinColumn(name = "id_autor")
	@NotNull
	private AutorEntity autor;
}
