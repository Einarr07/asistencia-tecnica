package com.asitenciatecnica.asistencia_tecnica.controller;

import com.asitenciatecnica.asistencia_tecnica.dto.ClienteDTO;
import com.asitenciatecnica.asistencia_tecnica.dto.TecnicoDTO;
import com.asitenciatecnica.asistencia_tecnica.dto.TicketDTO;
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
    public ResponseEntity<List<TicketDTO>> getAllTickets(){
        return ResponseEntity.ok(ticketService.findAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable int id){
        Ticket ticket = ticketService.findTicketById(id);

        if(ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setCodigo(ticket.getCodigo());
        ticketDTO.setDescripcion(ticket.getDescripcion());

        if(ticket.getTecnico() != null){
            TecnicoDTO tecnicoDTO = new TecnicoDTO();
            tecnicoDTO.setId_tecnico(ticket.getTecnico().getId_tecnico());
            tecnicoDTO.setNombre(ticket.getTecnico().getNombre());
            tecnicoDTO.setApellido(ticket.getTecnico().getApellido());
            ticketDTO.setTecnico(tecnicoDTO);
        }

        if  (ticket.getCliente() != null){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId_cliente(ticket.getCliente().getId_cliente());
            clienteDTO.setNombre(ticket.getCliente().getNombre());
            clienteDTO.setApellido(ticket.getCliente().getApellido());
            ticketDTO.setCliente(clienteDTO);
        }

        return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        ticketService.saveTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<TicketDTO> editTicket(@PathVariable int id, @RequestBody Ticket ticket){
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

        Ticket ticketActualizado = ticketService.updateTicket(ticketExistente);

        TicketDTO ticketDTO = mapToDTO(ticketActualizado);

        return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
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

    private TicketDTO mapToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setCodigo(ticket.getCodigo());
        dto.setDescripcion(ticket.getDescripcion());

        if (ticket.getTecnico() != null) {
            TecnicoDTO tecnicoDTO = new TecnicoDTO();
            tecnicoDTO.setId_tecnico(ticket.getTecnico().getId_tecnico());
            tecnicoDTO.setNombre(ticket.getTecnico().getNombre());
            tecnicoDTO.setApellido(ticket.getTecnico().getApellido());
            dto.setTecnico(tecnicoDTO);
        }

        if (ticket.getCliente() != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId_cliente(ticket.getCliente().getId_cliente());
            clienteDTO.setNombre(ticket.getCliente().getNombre());
            clienteDTO.setApellido(ticket.getCliente().getApellido());
            dto.setCliente(clienteDTO);
        }

        return dto;
    }

}
