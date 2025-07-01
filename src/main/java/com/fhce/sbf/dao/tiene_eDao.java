package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.tiene_eModel;

public interface tiene_eDao extends JpaRepository<tiene_eModel, Long> {

    // 1. Listar todos los registros
    @Query(value = "SELECT * FROM tiene_e", nativeQuery = true)
    List<tiene_eModel> listarTodos();

    // 2. Buscar todos los estados asociados a un ejemplar
    @Query(value = "SELECT * FROM tiene_e WHERE codigo = ?1", nativeQuery = true)
    List<tiene_eModel> buscarEstadosPorEjemplar(Long codigo);

    // 3. Buscar todos los ejemplares que tienen un estado específico
    @Query(value = "SELECT * FROM tiene_e WHERE id_estado = ?1", nativeQuery = true)
    List<tiene_eModel> buscarEjemplaresPorEstado(Long idEstado);

    // 4. Contar cuántos ejemplares tienen un estado específico
    @Query(value = "SELECT COUNT(*) FROM tiene_e WHERE id_estado = ?1", nativeQuery = true)
    Long contarEjemplaresPorEstado(Long idEstado);

    // 5. Verificar si una relación (codigo, id_estado) ya existe
    @Query(value = "SELECT COUNT(*) FROM tiene_e WHERE codigo = ?1 AND id_estado = ?2", nativeQuery = true)
    Long existeRelacion(Long codigo, Long idEstado);

    // 6. Ver estados con nombre para un ejemplar específico (JOIN)
    @Query(value = """
        SELECT t.codigo, e._01tipo AS estado
        FROM tiene_e t
        JOIN estado e ON t.id_estado = e.id_estado
        WHERE t.codigo = ?1
        """, nativeQuery = true)
    List<Object[]> verEstadosDeEjemplar(Long codigo);

    // 7. Ver todos los ejemplares con su estado y título del libro (JOIN)
    @Query(value = """
        SELECT t.codigo, l._01titulo AS titulo, e._01tipo AS estado
        FROM tiene_e t
        JOIN ejemplar x ON t.codigo = x.codigo
        JOIN libro l ON x._04id_libro = l.id_libro
        JOIN estado e ON t.id_estado = e.id_estado
        """, nativeQuery = true)
    List<Object[]> listarEjemplaresConEstadoYLibro();

    // 8. Contar cuántos estados distintos ha tenido un ejemplar
    @Query(value = "SELECT COUNT(DISTINCT id_estado) FROM tiene_e WHERE codigo = ?1", nativeQuery = true)
    Long contarEstadosDeEjemplar(Long codigo);
    
    @Query(value = "SELECT * FROM tiene_e WHERE codigo = ?1 AND id_estado = ?2", nativeQuery = true)
    tiene_eModel findByCodigoAndEstado(Long codigo, Long idEstado);

}
