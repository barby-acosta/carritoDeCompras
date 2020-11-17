package com.carritoDeCompras.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "dni")
	private String dni;

	@Column(name = "vip")
	private Boolean vip;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set<Carrito> carritos;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Long id, String dni, Boolean vip, Set<Carrito> carritos) {
		super();
		this.id = id;
		this.dni = dni;
		this.vip = vip;
		this.carritos = carritos;
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

}
