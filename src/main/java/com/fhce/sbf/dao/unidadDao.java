package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.unidadModel;

public interface unidadDao extends JpaRepository<unidadModel, Long> {

    // Buscar unidad por nombre exacto
    @Query(value = "SELECT * FROM unidad WHERE _01nombre = ?", nativeQuery = true)
    unidadModel findByNombre(String nombre);

    // Buscar unidades cuyo nombre contenga una palabra
    @Query(value = "SELECT * FROM unidad WHERE _01nombre LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<unidadModel> findByNombreLike(String nombre);

    // Buscar unidades por tipo (por ejemplo: 'biblioteca', 'archivo', etc.)
    @Query(value = "SELECT * FROM unidad WHERE _02tipo = ?", nativeQuery = true)
    List<unidadModel> findByTipo(String tipo);

    // Buscar unidades por facultad
    @Query(value = "SELECT * FROM unidad WHERE _03id_facultad = ?", nativeQuery = true)
    List<unidadModel> findByFacultad(Long idFacultad);

    // Contar unidades por tipo
    @Query(value = "SELECT COUNT(*) FROM unidad WHERE _02tipo = ?", nativeQuery = true)
    Long countByTipo(String tipo);

    // Listar todas las unidades
    @Query(value = "SELECT * FROM unidad", nativeQuery = true)
    List<unidadModel> listarUnidades();
    
 // Mostrar unidades junto con nombre de su facultad
    @Query(value = """
        SELECT u.*, f._01nombre AS facultad_nombre 
        FROM unidad u 
        JOIN facultad f ON u._03id_facultad = f.id_facultad
        """, nativeQuery = true)
    List<Object[]> listarUnidadesConFacultad();

}
