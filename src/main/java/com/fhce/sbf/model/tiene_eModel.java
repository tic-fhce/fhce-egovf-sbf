package com.fhce.sbf.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(TieneEId.class)
@Table(name = "tiene_e")
public class tiene_eModel {

    @Id
    @Column(name = "codigo")
    private Long codigo;

    @Id
    @Column(name = "id_estado")
    private Long id_estado;
}
