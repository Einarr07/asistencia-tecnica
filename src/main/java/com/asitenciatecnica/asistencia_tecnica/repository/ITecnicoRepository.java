package com.asitenciatecnica.asistencia_tecnica.repository;

import com.asitenciatecnica.asistencia_tecnica.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
