package com.carritoDeCompras.web.restController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/producto")
public class ProductoController {

//	private final Logger log = LoggerFactory.getLogger(ProductoController.class);
//
//	@Inject
//	private ProductoService productoService;
//
//	@GetMapping("/productos")
//	public ResponseEntity<List<ProductoDTO>> getAll(@PageableDefault(size = 2000) Pageable pageable)
//			throws URISyntaxException {
//		log.debug("REST request to get a page of Turnos");
//		Page<ProductoDTO> page = productoService.findAll(pageable);
//		return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
//	}
//
//	@GetMapping("/productos/{id}")
//	public ResponseEntity<ProductoDTO> getOne(@PathVariable Long id) {
//		log.debug("REST request to get Producto : {}", id);
//		ProductoDTO result = productoService.findOne(id);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@PostMapping("/productos")
//	public ResponseEntity<ProductoDTO> create(@Valid @RequestBody ProductoDTO productoDTO) throws URISyntaxException {
//		log.debug("REST request to save Producto : {}", productoDTO);
//		ProductoDTO result = productoService.save(productoDTO);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@PutMapping("/productos/{id}")
//	public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO)
//			throws URISyntaxException {
//		log.debug("REST request to update Producto : {}", productoDTO);
//		ProductoDTO result = productoService.update(id, productoDTO);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		log.debug("REST request to delete Producto : {}", id);
//		productoService.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

}
