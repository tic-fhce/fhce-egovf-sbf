package com.fhce.sbf.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tiene_e")
public class tiene_eModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "id_estado", nullable = false)
    private Long id_estado;
}
