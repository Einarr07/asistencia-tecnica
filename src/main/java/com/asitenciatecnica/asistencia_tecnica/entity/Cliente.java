package com.asitenciatecnica.asistencia_tecnica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;

    @Column(unique = true, nullable = false, length = 10)
    private String cedula;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String apellido;

    @Column(nullable = false, length = 20)
    private String ciudad;

    @Column(unique = true, nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 20)
    private String direccion;

    @Column(unique = true, nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false)
    private LocalDate fecha_nacimiento;

    @Column(length = 20)
    private String dependencia;
}
