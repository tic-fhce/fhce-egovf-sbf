package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.facultadModel;

public interface facultadDao extends JpaRepository<facultadModel, Long> {

    // Buscar facultad por nombre exacto
    @Query(value = "SELECT * FROM facultad WHERE _01nombre = ?", nativeQuery = true)
    facultadModel findByNombre(String nombre);

    // Buscar facultades cuyo nombre contenga cierto texto
    @Query(value = "SELECT * FROM facultad WHERE _01nombre LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<facultadModel> findByNombreContiene(String nombre);

    // Buscar facultad por ID
    @Query(value = "SELECT * FROM facultad WHERE id_facultad = ?", nativeQuery = true)
    facultadModel findByIdNativo(Long id);

    // Listar todas las facultades
    @Query(value = "SELECT * FROM facultad", nativeQuery = true)
    List<facultadModel> listarFacultades();

    // Contar cuántas facultades existen
    @Query(value = "SELECT COUNT(*) FROM facultad", nativeQuery = true)
    Long contarFacultades();

    // Listar solo nombres de facultades
    @Query(value = "SELECT _01nombre FROM facultad", nativeQuery = true)
    List<String> listarNombres();

}
