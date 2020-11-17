package com.carritoDeCompras.dto;

import java.util.Set;

import com.carritoDeCompras.domain.Carrito;

public class UsuarioDTO {

	private Long id;

	private String dni;

	private Boolean vip;

	private Set<Carrito> carritos;

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Boolean getVip() {
		return vip;
	}

	public void setVip(Boolean vip) {
		this.vip = vip;
	}

	public Set<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(Set<Carrito> carritos) {
		this.carritos = carritos;
	}

}
