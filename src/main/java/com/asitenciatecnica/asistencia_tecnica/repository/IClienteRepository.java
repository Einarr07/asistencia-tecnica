package com.asitenciatecnica.asistencia_tecnica.repository;

import com.asitenciatecnica.asistencia_tecnica.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
