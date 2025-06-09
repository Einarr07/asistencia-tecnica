package com.asitenciatecnica.asistencia_tecnica.controller;

import com.asitenciatecnica.asistencia_tecnica.entity.Tecnico;
import com.asitenciatecnica.asistencia_tecnica.service.TecnicoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<Tecnico>> getAllTecnico(){
        return ResponseEntity.ok(tecnicoService.findAllTecnico());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> getTecnicoById(@PathVariable int id){
        Tecnico tecnico = tecnicoService.findById(id);

        if(tecnico == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tecnico, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Tecnico> createTecnico(@RequestBody Tecnico tecnico){
        tecnicoService.saveTecnico(tecnico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Tecnico> updateTecnico(@PathVariable int id, @RequestBody Tecnico tecnico){
        Tecnico tecnicoExistente = tecnicoService.findById(id);

        if(tecnicoExistente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tecnicoExistente.setNombre(tecnico.getNombre());
        tecnicoExistente.setApellido(tecnico.getApellido());
        tecnicoExistente.setCedula(tecnico.getCedula());
        tecnicoExistente.setFecha_nacimiento(tecnico.getFecha_nacimiento());
        tecnicoExistente.setGenero(tecnico.getGenero());
        tecnicoExistente.setCiudad(tecnico.getCiudad());
        tecnicoExistente.setTelefono(tecnico.getTelefono());
        tecnicoExistente.setEmail(tecnico.getEmail());

        Tecnico tecnicoActualizado = tecnicoService.updateTecnico(tecnicoExistente);

        return new ResponseEntity<>(tecnicoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Tecnico> deleteTecnico(@PathVariable int id){
        Tecnico tecnicoExistente = tecnicoService.findById(id);

        if(tecnicoExistente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tecnicoService.deleteTecnico(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
