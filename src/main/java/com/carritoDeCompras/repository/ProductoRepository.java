package com.carritoDeCompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carritoDeCompras.domain.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	@Query(value = "select t from Producto t where t.id=?1")
	Producto findProductoById(Long id);

}