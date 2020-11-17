package com.carritoDeCompras.web.restController;


import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carritoDeCompras.domain.Producto;
import com.carritoDeCompras.dto.ProductoDTO;
import com.carritoDeCompras.service.ProductoService;

@CrossOrigin
@RestController
@RequestMapping("/producto")
public class ProductoController {

	private final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService productoService;

	@GetMapping("/reporte/{dni}")
	public ResponseEntity<List<Producto>> getReporte(@PathVariable String dni)
			throws URISyntaxException {
		log.debug("REST reporte");
		List<Producto> prod = productoService.getReporte(dni);
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	@GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> getAll(@PageableDefault(size =2000) Pageable pageable)
        throws URISyntaxException {
        log.debug("REST listado de productos");
        Page<ProductoDTO> page = productoService.findAll(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }  
}
