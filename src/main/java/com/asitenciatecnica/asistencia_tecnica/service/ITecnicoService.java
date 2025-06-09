package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.entity.Tecnico;

import java.util.List;

public interface ITecnicoService {

    public List<Tecnico> findAllTecnico();

    public Tecnico findById(int id);

    public void saveTecnico(Tecnico tecnico);

    public Tecnico updateTecnico(Tecnico tecnico);

    public void deleteTecnico(int id);
}
