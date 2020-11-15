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
	
	//GET 4 PRODUCTOS MAS CAROS

//	@GetMapping("/usuarios")
//	public ResponseEntity<List<UsuarioDTO>> getAll(@PageableDefault(size = 2000) Pageable pageable)
//			throws URISyntaxException {
//		log.debug("REST request to get a page of Turnos");
//		Page<UsuarioDTO> page = usuarioService.findAll(pageable);
//		return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
//	}
//
//	@GetMapping("/usuarios/{id}")
//	public ResponseEntity<UsuarioDTO> getOne(@PathVariable Long id) {
//		log.debug("REST request to get Usuario : {}", id);
//		UsuarioDTO result = usuarioService.findOne(id);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@PostMapping("/usuarios")
//	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
//		log.debug("REST request to save Usuario : {}", usuarioDTO);
//		UsuarioDTO result = usuarioService.save(usuarioDTO);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@PutMapping("/usuarios/{id}")
//	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO)
//			throws URISyntaxException {
//		log.debug("REST request to update Usuario : {}", usuarioDTO);
//		UsuarioDTO result = usuarioService.update(id, usuarioDTO);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		log.debug("REST request to delete Usuario : {}", id);
//		usuarioService.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

}
