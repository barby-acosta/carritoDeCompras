package com.carritoDeCompras.dto;

public class ProductoDTO {

	private Long id;

	private String nombre;

	private Float valor;

	public ProductoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ProductoDTO [id=" + id + ", nombre=" + nombre + ", valor=" + valor + "]";
	}

}
