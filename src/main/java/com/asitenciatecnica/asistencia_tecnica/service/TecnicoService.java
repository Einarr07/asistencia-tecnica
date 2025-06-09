package com.asitenciatecnica.asistencia_tecnica.service;

import com.asitenciatecnica.asistencia_tecnica.entity.Tecnico;
import com.asitenciatecnica.asistencia_tecnica.repository.ITecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService implements ITecnicoService{

    @Autowired
    private ITecnicoRepository tecnicoRepository;

    @Override
    public List<Tecnico> findAllTecnico() {
        return tecnicoRepository.findAll();
    }

    @Override
    public Tecnico findById(int id) {
        return tecnicoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTecnico(Tecnico tecnico) {
        tecnicoRepository.save(tecnico);
    }

    @Override
    public Tecnico updateTecnico(Tecnico tecnico) {
        this.saveTecnico(tecnico);
        return tecnico;
    }

    @Override
    public void deleteTecnico(int id) {
        tecnicoRepository.deleteById(id);
    }
}
