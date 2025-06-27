package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.estadoModel;

public interface estadoDao extends JpaRepository<estadoModel, Long> {

    // 1. Listar todos los estados
    @Query(value = "SELECT * FROM estado", nativeQuery = true)
    List<estadoModel> listarTodos();

    // 2. Buscar estado por tipo exacto
    @Query(value = "SELECT * FROM estado WHERE _01tipo = ?", nativeQuery = true)
    estadoModel findByTipo(String tipo);

    // 3. Buscar estados cuyo nombre contenga una palabra (LIKE)
    @Query(value = "SELECT * FROM estado WHERE _01tipo LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<estadoModel> findByTipoContiene(String tipo);

    // 4. Buscar estado por ID
    @Query(value = "SELECT * FROM estado WHERE id_estado = ?", nativeQuery = true)
    estadoModel findByIdNativo(Long id);

    // 5. Listar solo los nombres de los estados
    @Query(value = "SELECT _01tipo FROM estado", nativeQuery = true)
    List<String> listarNombresEstados();

    // 6. Contar cuántos estados distintos existen
    @Query(value = "SELECT COUNT(*) FROM estado", nativeQuery = true)
    Long contarEstados();

    // 7. JOIN: Listar todos los ejemplares con su tipo de estado
    @Query(value = """
        SELECT e.codigo, e._01estado, s.id_estado, s._01tipo
        FROM ejemplar e
        JOIN estado s ON e._01estado = s._01tipo
        """, nativeQuery = true)
    List<Object[]> listarEjemplaresConEstadoExtendido();

    // 8. JOIN: Contar cuántos ejemplares existen por cada estado registrado
    @Query(value = """
        SELECT s._01tipo AS estado, COUNT(e.codigo) AS cantidad
        FROM estado s
        LEFT JOIN ejemplar e ON e._01estado = s._01tipo
        GROUP BY s._01tipo
        ORDER BY cantidad DESC
        """, nativeQuery = true)
    List<Object[]> contarEjemplaresPorEstadoRegistrado();

    // 9. Buscar si un estado está siendo usado en ejemplares (existe en uso)
    @Query(value = """
        SELECT COUNT(*) 
        FROM ejemplar 
        WHERE _01estado = ?
        """, nativeQuery = true)
    Long contarUsoDelEstado(String tipo);

    // 10. Obtener tipos de estado que sí están en uso actualmente en ejemplar
    @Query(value = """
        SELECT DISTINCT s.*
        FROM estado s
        JOIN ejemplar e ON e._01estado = s._01tipo
        """, nativeQuery = true)
    List<estadoModel> listarEstadosEnUso();

}
