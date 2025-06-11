package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAllClientes();

    public Cliente findClienteById(int id);

    public void saveCliente(Cliente cliente);

    public Cliente updateCliente(Cliente cliente);

    public void deleteCliente(int id);
}
