package com.carritoDeCompras.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.carritoDeCompras.domain.Usuario;
import com.carritoDeCompras.dto.UsuarioDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper {

	UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

	List<UsuarioDTO> usuariosToUsuarioDTOs(List<Usuario> usuarios);

	Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

	List<Usuario> usuarioDTOsToUsuarios(List<UsuarioDTO> usuarioDTOs);

}