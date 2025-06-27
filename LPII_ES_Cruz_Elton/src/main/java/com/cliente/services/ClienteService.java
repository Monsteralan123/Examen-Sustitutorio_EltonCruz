package com.cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cliente.dtos.ClienteFilter;
import com.cliente.dtos.ResultadoResponse;
import com.cliente.models.Cliente;
import com.cliente.repositories.IClienteRepo;

@Service
public class ClienteService {

    @Autowired
    IClienteRepo _clienteRepo;

    public List<Cliente> getAll() {
        return _clienteRepo.findAllByOrderByCodClienteDesc();
    }

    public List<Cliente> search(ClienteFilter filtro) {
        return _clienteRepo.findAllWithFilters(filtro.getIdTipo());
    }

    public ResultadoResponse create(Cliente cliente) {
        try {
            Cliente registrado = _clienteRepo.save(cliente);
            String mensaje = String.format("Cliente %s registrado", registrado.getCodCliente());
            return new ResultadoResponse(true, mensaje);
        } catch (Exception ex) {
            return new ResultadoResponse(false, "Error al registrar cliente: " + ex.getMessage());
        }
    }

    public Cliente getOne(String cod) {
        return _clienteRepo.findById(cod).orElseThrow();
    }

    public ResultadoResponse update(Cliente cliente) {
        try {
            Cliente actualizado = _clienteRepo.save(cliente);
            String mensaje = String.format("Cliente %s actualizado", actualizado.getCodCliente());
            return new ResultadoResponse(true, mensaje);
        } catch (Exception ex) {
            return new ResultadoResponse(false, "Error al actualizar cliente: " + ex.getMessage());
        }
    }
}
