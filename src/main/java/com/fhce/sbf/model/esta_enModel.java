package com.fhce.sbf.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "esta_en")
public class esta_enModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstaEn;

    @Column(name = "id_libro", nullable = false)
    private Long idLibro;

    @Column(name = "id_prestamo", nullable = false)
    private Long idPrestamo;
    
    @Column(name = "id_ejemplar", nullable = false)
    private Long idEjemplar;
}
