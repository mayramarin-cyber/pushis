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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "libros")
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

	public LibroEntity() {
		this.categoria = new CategoriaEntity();
		this.editorial = new EditorialEntity();
		this.autor = new AutorEntity();
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getnPaginas() {
		return nPaginas;
	}

	public void setnPaginas(Integer nPaginas) {
		this.nPaginas = nPaginas;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	
	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public EditorialEntity getEditorial() {
		return editorial;
	}

	public void setEditorial(EditorialEntity editorial) {
		this.editorial = editorial;
	}

	public AutorEntity getAutor() {
		return autor;
	}

	public void setAutor(AutorEntity autor) {
		this.autor = autor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LibroEntity that = (LibroEntity) o;
		return Objects.equals(idLibro, that.idLibro);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLibro);
	}

}
