package com.carritoDeCompras.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carritoDeCompras.domain.Carrito;
import com.carritoDeCompras.domain.Producto;
import com.carritoDeCompras.domain.Usuario;
import com.carritoDeCompras.dto.CarritoDTO;
import com.carritoDeCompras.mapper.CarritoMapper;
import com.carritoDeCompras.model.Mensaje;
import com.carritoDeCompras.repository.CarritoRepository;
import com.carritoDeCompras.repository.ProductoRepository;
import com.carritoDeCompras.repository.UsuarioRepository;
import com.carritoDeCompras.web.exceptions.NotFoundRestException;

@Service
@Transactional
public class CarritoService {

	private final Logger log = LoggerFactory.getLogger(CarritoService.class);

	private final Boolean fechaPromo = false;

	@Autowired
	private CarritoRepository carritoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CarritoMapper carritoMapper;

	@Autowired
	private MessageSource messageSource;

	public Mensaje create(String dni) {
		log.debug("crear carrito para el usuario : {}", dni);
		Carrito c = new Carrito();
		Mensaje m = new Mensaje();
		if (dni != null) {
			Usuario usuario = usuarioRepository.findUsuarioByDni(dni);
			if (usuario != null) {
				if (usuario.getVip()) {
					c.setTipo("PROMOVIP");
				} else {
					if (fechaPromo) {
						c.setTipo("PROMOFECHA");
					} else {
						c.setTipo("COMUN");
					}
				}
				c.setUsuario(usuario);
				c.setEstado("PENDIENTE");
				c.setTotalcondescuento(new Float(0));
				c.setTotalsindescuento(new Float(0));
				c.setDescuento(0);

				c = carritoRepository.save(c);
				m.setId(c.getId());
				return m;
			} else {
				String errorMessage = messageSource.getMessage("error.get.not_found.message", new Object[] {},
						LocaleContextHolder.getLocale());
				String errorDescription = messageSource.getMessage("error.get.not_found.description", new Object[] {},
						LocaleContextHolder.getLocale());
				throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
			}
		} else {
			String errorMessage = messageSource.getMessage("error.get.not_found.message", new Object[] {},
					LocaleContextHolder.getLocale());
			String errorDescription = messageSource.getMessage("error.get.not_found.description", new Object[] {},
					LocaleContextHolder.getLocale());
			throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
		}

	}

	public void delete(Long id) {
		log.debug("Eliminar carrito : {}", id);
		if (id != null) {
			Carrito carrito = carritoRepository.findCarritoById(id);
			if (carrito != null) {
				carritoRepository.deleteById(id);
			} else {
				String errorMessage = messageSource.getMessage("error.delete.not_found.message", new Object[] {},
						LocaleContextHolder.getLocale());
				String errorDescription = messageSource.getMessage("error.delete.not_found.description",
						new Object[] {}, LocaleContextHolder.getLocale());
				throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
			}
		} else {
			String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
					LocaleContextHolder.getLocale());
			String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
					LocaleContextHolder.getLocale());
			throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
		}

	}

	public CarritoDTO agregar(Long prod_id, Long carrito_id) {
		if ((prod_id != null) && (carrito_id != null)) {
			Carrito c = carritoRepository.findCarritoById(carrito_id);
			Producto p = productoRepository.findProductoById(prod_id);

			c.getProductos().add(p);

			c.setTotalsindescuento(c.getTotalsindescuento() + p.getValor());
			c.setTotalcondescuento(c.getTotalsindescuento());
			c.setDescuento(0);

			c = getOferta(c);

			c = carritoRepository.save(c);
			CarritoDTO result = carritoMapper.carritoToCarritoDTO(c);
			return result;
		} else {
			String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
					LocaleContextHolder.getLocale());
			String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
					LocaleContextHolder.getLocale());
			throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
		}
	}

	private Carrito getOferta(Carrito c) {
		if (c.getProductos().size() > 5) {
			switch (c.getTipo()) {
			case "PROMOVIP":
				// recorro los productos hasta encontrar el mas barato
				Float min = menorValor(c.getProductos());
				// lo resto de total con desc
				c.setTotalcondescuento(c.getTotalcondescuento() - min);
				if (Float.compare(c.getTotalcondescuento(), new Float(700)) > 0) {
					c.setTotalcondescuento(c.getTotalcondescuento() - 700);
				} else {
					c.setTotalcondescuento(new Float(0));
				}
				break;
			case "PROMOFECHA":
				if (Float.compare(c.getTotalcondescuento(), new Float(500)) > 0) {
					c.setTotalcondescuento(c.getTotalcondescuento() - 500);
				} else {
					c.setTotalcondescuento(new Float(0));
				}
				break;
			case "COMUN":
				if (c.getProductos().size() == 10) {
					c.setDescuento(10);
					c.setTotalcondescuento(
							c.getTotalsindescuento() - (c.getTotalsindescuento() * c.getDescuento() / 100));
				}
				break;
			default:
				break;
			}
		}
		return c;
	}

	private Float menorValor(List<Producto> productos) {
		Float min = new Float(9999);
		for (Producto p : productos) {
			if (Float.compare(p.getValor(), min) < 0) {// si el producto vale menos que min
				min = p.getValor();
			}
		}
		return min;
	}

	public CarritoDTO eliminar(Long prod_id, Long carrito_id) {
		if ((prod_id != null) && (carrito_id != null)) {
			Carrito c = carritoRepository.findCarritoById(carrito_id);
			Producto p = productoRepository.findProductoById(prod_id);

			// eliminar producto
			c.getProductos().remove(p);

			c.setTotalsindescuento(c.getTotalsindescuento() - p.getValor());
			c.setTotalcondescuento(c.getTotalsindescuento());
			c.setDescuento(0);

			c = getOferta(c);

			c = carritoRepository.save(c);
			CarritoDTO result = carritoMapper.carritoToCarritoDTO(c);
			return result;
		} else {
			String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
					LocaleContextHolder.getLocale());
			String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
					LocaleContextHolder.getLocale());
			throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
		}
	}

	@Transactional(readOnly = true)
	public CarritoDTO findOne(Long id) {
		log.debug("Buscar carrito : {}", id);
		if (id != null) {
			Carrito carrito = carritoRepository.findCarritoById(id);
			if (carrito != null) {
				CarritoDTO result = carritoMapper.carritoToCarritoDTO(carrito);
				return result;
			} else {
				String errorMessage = messageSource.getMessage("error.get.not_found.message", new Object[] {},
						LocaleContextHolder.getLocale());
				String errorDescription = messageSource.getMessage("error.get.not_found.description", new Object[] {},
						LocaleContextHolder.getLocale());
				throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
			}
		} else {
			String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
					LocaleContextHolder.getLocale());
			String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
					LocaleContextHolder.getLocale());
			throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
		}
	}

	public CarritoDTO finalizar(Long id) {
		if (id != null) {
			Carrito carrito = carritoRepository.findCarritoById(id);
			if (carrito != null) {
				carrito.setEstado("CERRADO");
				CarritoDTO result = carritoMapper.carritoToCarritoDTO(carrito);
				return result;
			} else {
				String errorMessage = messageSource.getMessage("error.get.not_found.message", new Object[] {},
						LocaleContextHolder.getLocale());
				String errorDescription = messageSource.getMessage("error.get.not_found.description", new Object[] {},
						LocaleContextHolder.getLocale());
				throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
			}
		} else {
			String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
					LocaleContextHolder.getLocale());
			String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
					LocaleContextHolder.getLocale());
			throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
		}
	}

}

//public CarritoDTO agregar(Long carrito_id, List<Producto> productos) {
//if (carrito_id != null) {
//	Carrito c = carritoRepository.findCarritoById(carrito_id);
//	if (c != null) {
//		for (Producto p : productos) {
//			c.getProductos().add(p);
//			c.setTotalsindescuento(c.getTotalsindescuento() + p.getValor());
//		}
//		c.setTotalcondescuento(c.getTotalsindescuento());
//		c.setDescuento(0);
//		c = getOferta(c);
//		c = carritoRepository.save(c);
//		CarritoDTO result = carritoMapper.carritoToCarritoDTO(c);
//		return result;
//	} else {
//		String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
//				LocaleContextHolder.getLocale());
//		String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
//				LocaleContextHolder.getLocale());
//		throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
//	}
//} else {
//	String errorMessage = messageSource.getMessage("error.param_required.message", new Object[] {},
//			LocaleContextHolder.getLocale());
//	String errorDescription = messageSource.getMessage("error.param_required.description", new Object[] {},
//			LocaleContextHolder.getLocale());
//	throw new NotFoundRestException(errorMessage, errorDescription, new ArrayList<>());
//}
//}
