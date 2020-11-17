package com.carritoDeCompras.web.restController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carritoDeCompras.dto.CarritoDTO;
import com.carritoDeCompras.model.Mensaje;
import com.carritoDeCompras.service.CarritoService;

@CrossOrigin
@RestController
@RequestMapping("/carrito")
public class CarritoController {

	private final Logger log = LoggerFactory.getLogger(CarritoController.class);

	@Autowired
	private CarritoService carritoService;

	@GetMapping("/create/{dni}")
	public ResponseEntity<Mensaje> create(@PathVariable String dni) {
		log.debug("REST crear carrito del usuario: {}", dni);
		Mensaje result = carritoService.create(dni);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/carritos/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.debug("REST eliminar el carrito: {}", id);
		carritoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/add/{prod_id}/{carrito_id}")
	public ResponseEntity<CarritoDTO> addProduct(@PathVariable Long prod_id, @PathVariable Long carrito_id) {
		log.debug("REST agregar el producto {} al carrito {}", prod_id, carrito_id);
		CarritoDTO result = carritoService.agregar(prod_id,carrito_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/remove/{prod_id}/{carrito_id}")
	public ResponseEntity<CarritoDTO> removeProduct(@PathVariable Long prod_id, @PathVariable Long carrito_id) {
		log.debug("REST agregar el producto {} al carrito {}", prod_id, carrito_id);
		CarritoDTO result = carritoService.eliminar(prod_id,carrito_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// consultar estado de carrito
	@GetMapping("/carritos/{id}")
	public ResponseEntity<CarritoDTO> getOne(@PathVariable Long id) {
		log.debug("REST buscar Carrito : {}", id);
		CarritoDTO result = carritoService.findOne(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	//finalizar compra
	@GetMapping("/finalizar/{id}")
	public ResponseEntity<CarritoDTO> finalizar(@PathVariable Long id) {
		log.debug("REST finalizar Carrito : {}", id);
		CarritoDTO result = carritoService.finalizar(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

//@PostMapping("/add/{carrito_id}")
//public ResponseEntity<CarritoDTO> addProduct(@PathVariable Long carrito_id,  @Valid @RequestBody List<Producto> productos) {
//	log.debug("REST agregar productos al carrito {}", carrito_id);
//	CarritoDTO result = carritoService.agregar(carrito_id, productos);
//	return new ResponseEntity<>(result, HttpStatus.OK);
//}
