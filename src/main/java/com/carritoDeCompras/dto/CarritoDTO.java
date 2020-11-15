package com.carritoDeCompras.dto;

import java.util.Set;

import com.carritoDeCompras.domain.Producto;

public class CarritoDTO {

	private Long id;

//	private String tipo;
	// comun, promofecha, promovip

	private String estado;
	// pendiente, cerrado

	private Float totalcondescuento;
	// total a pagar por el usuario

//	private Integer descuento;
	// descuento otorgado

//	private Float totalsindescuento;
	// total de la compra

//	private Usuario usuario;

	private Set<Producto> productos;

	public CarritoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Float getTotalcondescuento() {
		return totalcondescuento;
	}

	public void setTotalcondescuento(Float totalcondescuento) {
		this.totalcondescuento = totalcondescuento;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "CarritoDTO [id=" + id + ", estado=" + estado + ", totalcondescuento=" + totalcondescuento
				+ ", productos=" + productos + "]";
	}

}
