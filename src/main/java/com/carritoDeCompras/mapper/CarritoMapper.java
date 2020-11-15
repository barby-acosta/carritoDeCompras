package com.carritoDeCompras.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.carritoDeCompras.domain.Carrito;
import com.carritoDeCompras.dto.CarritoDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CarritoMapper {

	CarritoDTO carritoToCarritoDTO(Carrito carrito);

	List<CarritoDTO> carritosToCarritoDTOs(List<Carrito> carritos);

	Carrito carritoDTOToCarrito(CarritoDTO carritoDTO);

	List<Carrito> carritoDTOsToCarritos(List<CarritoDTO> carritoDTOs);
	
}