package com.fhce.sbf.model;

import java.io.Serializable;
import java.util.Objects;

public class TieneEId implements Serializable {

    private Long codigo;
    private Long id_estado;

    public TieneEId() {}

    public TieneEId(Long codigo, Long id_estado) {
        this.codigo = codigo;
        this.id_estado = id_estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TieneEId)) return false;
        TieneEId that = (TieneEId) o;
        return Objects.equals(codigo, that.codigo) &&
               Objects.equals(id_estado, that.id_estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, id_estado);
    }
}
