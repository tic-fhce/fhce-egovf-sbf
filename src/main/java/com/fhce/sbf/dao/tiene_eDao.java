package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.tiene_eModel;

public interface tiene_eDao extends JpaRepository<tiene_eModel, Long> {

    // 1. Listar todos los registros de relación entre ejemplares y estados
    @Query(value = "SELECT * FROM tiene_e", nativeQuery = true)
    List<tiene_eModel> listarTodos();

    // 2. Buscar estados de un ejemplar específico
    @Query(value = "SELECT * FROM tiene_e WHERE codigo = ?", nativeQuery = true)
    List<tiene_eModel> buscarEstadosPorEjemplar(Long codigo);

    // 3. Buscar ejemplares que tienen un estado específico
    @Query(value = "SELECT * FROM tiene_e WHERE id_estado = ?", nativeQuery = true)
    List<tiene_eModel> buscarEjemplaresPorEstado(Long idEstado);

    // 4. Contar cuántos ejemplares tienen un estado específico
    @Query(value = "SELECT COUNT(*) FROM tiene_e WHERE id_estado = ?", nativeQuery = true)
    Long contarEjemplaresPorEstado(Long idEstado);

    // 5. Verificar si una relación específica ya existe (evitar duplicados)
    @Query(value = "SELECT COUNT(*) FROM tiene_e WHERE codigo = ?1 AND id_estado = ?2", nativeQuery = true)
    Long existeRelacion(Long codigo, Long idEstado);

    // 6. JOIN: Ver estados con nombre para un ejemplar específico
    @Query(value = """
        SELECT t.codigo, e._01tipo AS estado
        FROM tiene_e t
        JOIN estado e ON t.id_estado = e.id_estado
        WHERE t.codigo = ?
        """, nativeQuery = true)
    List<Object[]> verEstadosDeEjemplar(Long codigo);

    // 7. JOIN: Ver todos los ejemplares con su estado
    @Query(value = """
        SELECT t.codigo, l._01titulo AS titulo, e._01tipo AS estado
        FROM tiene_e t
        JOIN ejemplar x ON t.codigo = x.codigo
        JOIN libro l ON x._04id_libro = l.id_libro
        JOIN estado e ON t.id_estado = e.id_estado
        """, nativeQuery = true)
    List<Object[]> listarEjemplaresConEstadoYLibro();

    // 8. Contar estados distintos que ha tenido un ejemplar
    @Query(value = "SELECT COUNT(DISTINCT id_estado) FROM tiene_e WHERE codigo = ?", nativeQuery = true)
    Long contarEstadosDeEjemplar(Long codigo);
}
