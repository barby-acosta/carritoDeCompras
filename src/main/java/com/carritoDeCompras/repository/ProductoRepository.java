package com.carritoDeCompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carritoDeCompras.domain.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	@Query(value = "select t from Producto t where t.id=?1")
	Producto findProductoById(Long id);

	@Query(value = "SELECT DISTINCT p.* FROM Usuario u INNER JOIN Carrito c ON u.id=c.usuario_id INNER JOIN Carritoproducto cp ON cp.carrito_id=c.id INNER JOIN Producto p ON cp.producto_id=p.id WHERE u.dni= ?1 AND c.estado='CERRADO' ORDER BY p.valor DESC LIMIT 4", nativeQuery = true)
	List<Producto> getReporte(String dni);

}