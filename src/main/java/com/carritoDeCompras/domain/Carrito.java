package com.carritoDeCompras.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tipo")
	private String tipo;
	// comun, promofecha, promovip

	@Column(name = "estado")
	private String estado;
	// nuevo, pendiente, cerrado

	@Column(name = "totalcondescuento")
	private Float totalcondescuento;
	// total a pagar por el usuario

	@Column(name = "descuento")
	private Integer descuento;
	// descuento otorgado

	@Column(name = "totalsindescuento")
	private Float totalsindescuento;
	// total de la compra

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToMany
	@JoinTable(name = "carritoproducto", joinColumns = @JoinColumn(name = "carrito_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
	private List<Producto> productos;

	public Carrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Carrito(Long id, String tipo, String estado, Float totalcondescuento, Integer descuento, Float totalsindescuento,
			Usuario usuario, List<Producto> productos) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.totalcondescuento = totalcondescuento;
		this.descuento = descuento;
		this.totalsindescuento = totalsindescuento;
		this.usuario = usuario;
		this.productos = productos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public Float getTotalsindescuento() {
		return totalsindescuento;
	}

	public void setTotalsindescuento(Float totalsindescuento) {
		this.totalsindescuento = totalsindescuento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
