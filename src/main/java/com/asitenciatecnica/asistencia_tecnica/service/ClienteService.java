package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.entity.Cliente;
import com.asitenciatecnica.asistencia_tecnica.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findClienteById(int id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        this.saveCliente(cliente);
        return cliente;
    }

    @Override
    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }
}
