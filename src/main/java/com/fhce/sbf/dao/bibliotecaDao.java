package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.bibliotecaModel;

public interface bibliotecaDao extends JpaRepository<bibliotecaModel, Long> {

    // Listar todas las bibliotecas
    @Query(value = "SELECT * FROM biblioteca", nativeQuery = true)
    List<bibliotecaModel> findAll();

    // Buscar bibliotecas por nombre
    @Query(value = "SELECT * FROM biblioteca WHERE _01nombre LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<bibliotecaModel> findByNombreLike(String nombre);

    // Buscar bibliotecas por id_facultad
    @Query(value = "SELECT * FROM biblioteca WHERE _06id_facultad = ?", nativeQuery = true)
    List<bibliotecaModel> findByFacultad(Long idFacultad);

    // Contar bibliotecas por facultad
    @Query(value = "SELECT COUNT(*) FROM biblioteca WHERE _06id_facultad = ?", nativeQuery = true)
    Long countByFacultad(Long idFacultad);

    // Buscar bibliotecas por coincidencia de horario
    @Query(value = "SELECT * FROM biblioteca WHERE _05horario_atencion LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<bibliotecaModel> findByHorarioAtencion(String horario);
}
