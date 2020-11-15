package com.carritoDeCompras.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.carritoDeCompras.domain.Producto;
import com.carritoDeCompras.dto.ProductoDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ProductoMapper {

	ProductoDTO productoToProductoDTO(Producto producto);

	List<ProductoDTO> productosToProductoDTOs(List<Producto> productos);

	Producto productoDTOToProducto(ProductoDTO productoDTO);

	List<Producto> productoDTOsToProductos(List<ProductoDTO> productoDTOs);

}