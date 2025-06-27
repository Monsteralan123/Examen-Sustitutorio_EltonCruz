package com.cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cliente.models.TipoCliente;
import com.cliente.repositories.ITipoClienteRepo;

@Service
public class TipoService {

	
	@Autowired
	ITipoClienteRepo _tipoRepo;
	
	public List<TipoCliente> getAll(){
		return _tipoRepo.findAllByOrderByIdTipo();
		
	}
}
