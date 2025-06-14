package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.entity.Ticket;
import com.asitenciatecnica.asistencia_tecnica.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITikectService{

    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
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
}
