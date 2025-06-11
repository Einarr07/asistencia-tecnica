package com.asitenciatecnica.asistencia_tecnica.controller;

import com.asitenciatecnica.asistencia_tecnica.entity.Cliente;
import com.asitenciatecnica.asistencia_tecnica.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCliente() {
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        Cliente cliente = clienteService.findClienteById(id);

        if(cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Cliente> editCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteService.findClienteById(id);

        if(clienteExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clienteExistente.setCedula( cliente.getCedula());
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setCiudad(cliente.getCiudad());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setFecha_nacimiento(cliente.getFecha_nacimiento());
        clienteExistente.setDependencia(cliente.getDependencia());

        Cliente clienteActualizado = clienteService.updateCliente(clienteExistente);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable int id) {
        Cliente cliente = clienteService.findClienteById(id);

        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
