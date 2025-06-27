package com.cliente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cliente.models.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, String> {

	List<Cliente> findAllByOrderByCodClienteDesc();

	List<Cliente> findAllByTipoCliente_IdTipoOrderByCodClienteDesc(Integer idTipo);

	@Query("""
			    SELECT c FROM Cliente c
			    WHERE (:idTipo IS NULL OR c.tipoCliente.idTipo = :idTipo)
			    ORDER BY c.codCliente DESC
			""")
	List<Cliente> findAllWithFilters(@Param("idTipo") Integer idTipo);

}
