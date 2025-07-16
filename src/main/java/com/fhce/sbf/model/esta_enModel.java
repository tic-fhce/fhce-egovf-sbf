package com.fhce.sbf.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "esta_en")
public class esta_enModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_esta_en; 

    @Column(name = "id_libro", nullable = false)
    private Long id_libro;

    @Column(name = "id_prestamo", nullable = false)
    private Long id_prestamo;
}