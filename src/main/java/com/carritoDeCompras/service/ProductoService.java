package com.carritoDeCompras.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carritoDeCompras.domain.Producto;
import com.carritoDeCompras.dto.ProductoDTO;
import com.carritoDeCompras.mapper.ProductoMapper;
import com.carritoDeCompras.repository.ProductoRepository;
import com.carritoDeCompras.web.exceptions.NotFoundRestException;

@Service
@Transactional
public class ProductoService {

	private final Logger log = LoggerFactory.getLogger(ProductoService.class);

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoMapper productoMapper;

	@Autowired
	private MessageSource messageSource;

	@Transactional(readOnly = true)
	public List<Producto> getReporte(String dni) {
		log.debug("Request Reporte");
		if (dni != null) {
			List<Producto> result = productoRepository.getReporte(dni);
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
	public Page<ProductoDTO> findAll(Pageable pageable) {
		log.debug("Request findAll productos");
		Page<Producto> result = productoRepository.findAll(pageable);
		return result.map(producto -> productoMapper.productoToProductoDTO(producto));
	}
}
