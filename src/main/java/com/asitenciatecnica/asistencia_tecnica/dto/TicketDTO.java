package com.asitenciatecnica.asistencia_tecnica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketDTO {

    private int id;
    private int codigo;
    private String descripcion;
    private String nombreTecnico;
    private String apellidoTecnico;
    private String nombreCliente;
    private String apellidoCliente;
}
