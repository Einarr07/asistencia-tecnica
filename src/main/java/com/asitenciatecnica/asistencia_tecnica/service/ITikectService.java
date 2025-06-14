package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.entity.Ticket;

import java.util.List;

public interface ITikectService {

    public List<Ticket> findAllTickets();

    public Ticket findTicketById(int id);

    public void saveTicket(Ticket ticket);

    public Ticket updateTicket(Ticket ticket);

    public void deleteTicket(int id);
}
