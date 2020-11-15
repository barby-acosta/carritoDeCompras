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

import com.carritoDeCompras.domain.Usuario;
import com.carritoDeCompras.dto.UsuarioDTO;
import com.carritoDeCompras.mapper.UsuarioMapper;
import com.carritoDeCompras.repository.UsuarioRepository;
import com.carritoDeCompras.web.exceptions.NotFoundRestException;

@Service
@Transactional
public class UsuarioService {

	private final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	@Autowired
	private MessageSource messageSource;

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Usuarios");
		Page<Usuario> result = usuarioRepository.findAll(pageable);
		return result.map(usuario -> usuarioMapper.usuarioToUsuarioDTO(usuario));
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findOne(Long id) {
		log.debug("Request to get Usuario : {}", id);
		if (id != null) {
			Usuario usuario = usuarioRepository.findUsuarioById(id);
			if (usuario != null) {
				UsuarioDTO result = usuarioMapper.usuarioToUsuarioDTO(usuario);
				return result;
			} else {
				String errorMessage = messageSource.getMessage("error.get.not_found.message", new Object[] {},
						LocaleContextHolder.getLocale());
				String errorDescription = messageSource.getMessage("error.get.not_found.description", new Object[] {}, LocaleContextHolder.getLocale());
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

	
	public UsuarioDTO save(UsuarioDTO usuarioDTO) {
		log.debug("Request to save Usuario cambio : {}", usuarioDTO);
		Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
		usuario = usuarioRepository.save(usuario);
		UsuarioDTO result = usuarioMapper.usuarioToUsuarioDTO(usuario);
		return result;
	}

	public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
		log.debug("Request to update Usuario cambio : {}", usuarioDTO);
		if (id != null) {
			Usuario usuarioBd = usuarioRepository.findUsuarioById(id);
			if (usuarioBd != null) {
				Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
				usuario.setId(id);
				usuario = usuarioRepository.save(usuario);
				UsuarioDTO result = usuarioMapper.usuarioToUsuarioDTO(usuario);
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
		log.debug("Request to delete Usuario : {}", id);
		if (id != null) {
			Usuario usuario = usuarioRepository.findUsuarioById(id);
			if (usuario != null) {
				usuarioRepository.delete(usuario);
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
