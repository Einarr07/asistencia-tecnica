package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.dto.ClienteDTO;
import com.asitenciatecnica.asistencia_tecnica.dto.TecnicoDTO;
import com.asitenciatecnica.asistencia_tecnica.dto.TicketDTO;
import com.asitenciatecnica.asistencia_tecnica.entity.Ticket;
import com.asitenciatecnica.asistencia_tecnica.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    public List<TicketDTO> findAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Ticket findTicketById(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        this.saveTicket(ticket);
        return ticket;
    }

    @Override
    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }

    private TicketDTO convertToDTO(Ticket ticket) {

        TicketDTO dto = new TicketDTO();

        dto.setId(ticket.getId());
        dto.setCodigo(ticket.getCodigo());
        dto.setDescripcion(ticket.getDescripcion());

        if(ticket.getTecnico() != null){
            TecnicoDTO tecnicoDTO = new TecnicoDTO();
            tecnicoDTO.setId_tecnico(ticket.getTecnico().getId_tecnico());
            tecnicoDTO.setNombre(ticket.getTecnico().getNombre());
            tecnicoDTO.setApellido(ticket.getTecnico().getApellido());
            dto.setTecnico(tecnicoDTO);
        }

        if  (ticket.getCliente() != null){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId_cliente(ticket.getCliente().getId_cliente());
            clienteDTO.setNombre(ticket.getCliente().getNombre());
            clienteDTO.setApellido(ticket.getCliente().getApellido());
            dto.setCliente(clienteDTO);
        }

        return dto;
    }
}
