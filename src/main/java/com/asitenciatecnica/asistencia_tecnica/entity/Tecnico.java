package com.asitenciatecnica.asistencia_tecnica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tecnicos")
@Getter @Setter
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tecnico;

    @Column(length = 20, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String apellido;

    @Column(unique = true, length = 10, nullable = false)
    private String cedula;

    @Column(nullable = false)
    private LocalDate fecha_nacimiento;

    @Column(length = 10, nullable = false)
    private String genero;

    @Column(length = 20, nullable = false)
    private String ciudad;

    @Column(length = 20, nullable = false)
    private String direccion;

    @Column(unique = true, length = 10, nullable = false)
    private String telefono;

    @Column(unique = true, length = 45, nullable = false)
    private String email;

}
