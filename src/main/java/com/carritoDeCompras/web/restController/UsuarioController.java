package com.carritoDeCompras.web.restController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carritoDeCompras.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	//implementar ABM usuarios
	
	
}
