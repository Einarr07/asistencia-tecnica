package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.dto.TicketDTO;
import com.asitenciatecnica.asistencia_tecnica.entity.Ticket;

import java.util.List;

public interface ITicketService {

    public List<TicketDTO> findAllTickets();

    public Ticket findTicketById(int id);

    public void saveTicket(Ticket ticket);

    public Ticket updateTicket(Ticket ticket);

    public void deleteTicket(int id);
}
