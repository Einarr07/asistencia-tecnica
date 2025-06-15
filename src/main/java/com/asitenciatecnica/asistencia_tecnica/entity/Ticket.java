package com.asitenciatecnica.asistencia_tecnica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter @Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private int codigo;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_tecnico")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
