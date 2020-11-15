package com.carritoDeCompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carritoDeCompras.domain.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

	@Query(value = "select t from Carrito t where t.id=?1")
	Carrito findCarritoById(Long id);

}