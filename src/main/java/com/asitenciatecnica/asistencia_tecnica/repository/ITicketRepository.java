package com.asitenciatecnica.asistencia_tecnica.repository;

import com.asitenciatecnica.asistencia_tecnica.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Integer> {

}
