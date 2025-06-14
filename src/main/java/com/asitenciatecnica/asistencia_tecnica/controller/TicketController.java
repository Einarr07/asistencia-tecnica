package com.asitenciatecnica.asistencia_tecnica.controller;

import com.asitenciatecnica.asistencia_tecnica.entity.Cliente;
import com.asitenciatecnica.asistencia_tecnica.entity.Tecnico;
import com.asitenciatecnica.asistencia_tecnica.entity.Ticket;
import com.asitenciatecnica.asistencia_tecnica.repository.IClienteRepository;
import com.asitenciatecnica.asistencia_tecnica.repository.ITecnicoRepository;
import com.asitenciatecnica.asistencia_tecnica.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ITecnicoRepository tecnicoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(){
        return ResponseEntity.ok(ticketService.findAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id){
        Ticket ticket = ticketService.findTicketById(id);

        if(ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Ticket> editTicket(@PathVariable int id, @RequestBody Ticket ticket){
        Ticket ticketExistente = ticketService.findTicketById(id);

        if(ticketExistente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ticketExistente.setCodigo(ticket.getCodigo());
        ticketExistente.setDescripcion(ticket.getDescripcion());

        if (ticket.getTecnico()!=null && ticket.getTecnico().getId_tecnico() != 0){
            Tecnico tecnico = tecnicoRepository.findById(ticket.getTecnico().getId_tecnico()).orElse(null);

            if (tecnico != null){
                ticketExistente.setTecnico(tecnico);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        if (ticket.getCliente() != null && ticket.getCliente().getId_cliente() != 0){
            Cliente cliente = clienteRepository.findById(ticket.getCliente().getId_cliente()).orElse(null);

            if (cliente != null){
                ticketExistente.setCliente(cliente);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable int id){
        Ticket ticket = ticketService.findTicketById(id);

        if (ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ticketService.deleteTicket(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
