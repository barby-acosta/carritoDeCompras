package com.carritoDeCompras.service;

import java.util.ArrayList;

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
	public Page<ProductoDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Productos");
		Page<Producto> result = productoRepository.findAll(pageable);
		return result.map(producto -> productoMapper.productoToProductoDTO(producto));
	}

	@Transactional(readOnly = true)
	public ProductoDTO findOne(Long id) {
		log.debug("Request to get Producto : {}", id);
		if (id != null) {
			Producto producto = productoRepository.findProductoById(id);
			if (producto != null) {
				ProductoDTO result = productoMapper.productoToProductoDTO(producto);
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

	public ProductoDTO save(ProductoDTO productoDTO) {
		log.debug("Request to save Producto cambio : {}", productoDTO);
		Producto producto = productoMapper.productoDTOToProducto(productoDTO);
		producto = productoRepository.save(producto);
		ProductoDTO result = productoMapper.productoToProductoDTO(producto);
		return result;
	}

	public ProductoDTO update(Long id, ProductoDTO productoDTO) {
		log.debug("Request to update Producto cambio : {}", productoDTO);
		if (id != null) {
			Producto productoBd = productoRepository.findProductoById(id);
			if (productoBd != null) {
				Producto producto = productoMapper.productoDTOToProducto(productoDTO);
				producto.setId(id);
				producto = productoRepository.save(producto);
				ProductoDTO result = productoMapper.productoToProductoDTO(producto);
				return result;
			} else {
				String errorMessage = messageSource.getMessage("error.update.not_found.message", new Object[] {},
						LocaleContextHolder.getLocale());
				String errorDescription = messageSource.getMessage("error.update.not_found.description",
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

	public void delete(Long id) {
		log.debug("Request to delete Producto : {}", id);
		if (id != null) {
			Producto producto = productoRepository.findProductoById(id);
			if (producto != null) {
				productoRepository.delete(producto);
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

}
