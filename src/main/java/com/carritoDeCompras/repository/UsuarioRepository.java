package com.carritoDeCompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carritoDeCompras.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select t from Usuario t where t.id=?1")
	Usuario findUsuarioById(Long id);

	@Query(value = "select t from Usuario t where t.dni=?1")
	Usuario findUsuarioByDni(String dni);

}
