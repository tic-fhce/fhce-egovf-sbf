package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.ejemplarModel;

public interface ejemplarDao extends JpaRepository<ejemplarModel, Long> {

    // 1. Listar todos los ejemplares
    @Query(value = "SELECT * FROM ejemplar", nativeQuery = true)
    List<ejemplarModel> listarTodos();

    // 2. Buscar ejemplares por estado (ej: disponible, prestado)
    @Query(value = "SELECT * FROM ejemplar WHERE _01estado = ?", nativeQuery = true)
    List<ejemplarModel> findByEstado(String estado);

    // 3. Buscar ejemplares por ID del libro
    @Query(value = "SELECT * FROM ejemplar WHERE _04id_libro = ?", nativeQuery = true)
    List<ejemplarModel> findByLibro(Long idLibro);

    // 4. Contar ejemplares por libro
    @Query(value = "SELECT COUNT(*) FROM ejemplar WHERE _04id_libro = ?", nativeQuery = true)
    Long countByLibro(Long idLibro);

    // 5. Contar ejemplares por estado
    @Query(value = "SELECT COUNT(*) FROM ejemplar WHERE _01estado = ?", nativeQuery = true)
    Long countByEstado(String estado);

    // 6. Obtener direcciones de archivo
    @Query(value = "SELECT _03direccion FROM ejemplar", nativeQuery = true)
    List<String> listarDirecciones();

    // 7. Obtener portadas
    @Query(value = "SELECT _02portada FROM ejemplar", nativeQuery = true)
    List<String> listarPortadas();

    // 8. Obtener ejemplares con el título del libro (JOIN)
    @Query(value = """
        SELECT e.*, l._01titulo AS titulo_libro
        FROM ejemplar e
        JOIN libro l ON e._04id_libro = l.id_libro
        """, nativeQuery = true)
    List<Object[]> listarEjemplaresConTituloLibro();

    // 9. Obtener cantidad de ejemplares por libro con título (agrupado)
    @Query(value = """
        SELECT l._01titulo, COUNT(e.codigo) AS cantidad
        FROM ejemplar e
        JOIN libro l ON e._04id_libro = l.id_libro
        GROUP BY l._01titulo
        ORDER BY cantidad DESC
        """, nativeQuery = true)
    List<Object[]> contarEjemplaresPorLibro();

    // 10. Obtener estados de ejemplares agrupados y contados
    @Query(value = """
        SELECT _01estado, COUNT(*) AS total
        FROM ejemplar
        GROUP BY _01estado
        ORDER BY total DESC
        """, nativeQuery = true)
    List<Object[]> contarEjemplaresPorEstado();

    // 11. Buscar ejemplares que contienen palabra clave en dirección
    @Query(value = "SELECT * FROM ejemplar WHERE _03direccion LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<ejemplarModel> buscarPorDireccion(String palabraClave);

    // 12. Buscar ejemplares con portada no nula
    @Query(value = "SELECT * FROM ejemplar WHERE _02portada IS NOT NULL", nativeQuery = true)
    List<ejemplarModel> ejemplaresConPortada();

    // 13. Buscar ejemplares con archivos disponibles (dirección no nula)
    @Query(value = "SELECT * FROM ejemplar WHERE _03direccion IS NOT NULL", nativeQuery = true)
    List<ejemplarModel> ejemplaresConArchivo();

}
