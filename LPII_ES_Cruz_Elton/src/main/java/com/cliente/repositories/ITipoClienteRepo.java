package com.cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.models.TipoCliente;

public interface ITipoClienteRepo extends JpaRepository<TipoCliente,Integer >{
	
	

}
