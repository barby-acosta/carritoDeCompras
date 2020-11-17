package com.carritoDeCompras.dto;

import java.util.List;

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

	private Float totalsindescuento;
	// total de la compra

//	private Usuario usuario;

	private List<Producto> productos;

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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Float getTotalsindescuento() {
		return totalsindescuento;
	}

	public void setTotalsindescuento(Float totalsindescuento) {
		this.totalsindescuento = totalsindescuento;
	}
	
}
