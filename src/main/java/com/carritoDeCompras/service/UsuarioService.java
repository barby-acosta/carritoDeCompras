package com.carritoDeCompras.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carritoDeCompras.mapper.ProductoMapper;
import com.carritoDeCompras.repository.ProductoRepository;

@Service
@Transactional
public class UsuarioService {

	private final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private ProductoMapper productoMapper;

	@Autowired
	private MessageSource messageSource;
	
	//implementar ABM usuarios


}
