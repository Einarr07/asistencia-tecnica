package com.asitenciatecnica.asistencia_tecnica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketDTO {

    private int id;
    private int codigo;
    private String descripcion;
    private TecnicoDTO tecnico;
    private ClienteDTO cliente;
}
