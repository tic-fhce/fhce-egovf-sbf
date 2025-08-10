package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.sbf.model.lectorModel;

public interface lectorDao extends JpaRepository<lectorModel, Long> {

    // Buscar lector por CI
    @Query(value = "SELECT * FROM lector WHERE _04ci = ?", nativeQuery = true)
    lectorModel findByCi(int ci);

    // Buscar lector por RU
    @Query(value = "SELECT * FROM lector WHERE _05ru = ?", nativeQuery = true)
    lectorModel findByRu(int ru);

    // Buscar lector por nombre exacto
    @Query(value = "SELECT * FROM lector WHERE _01nombre = ?", nativeQuery = true)
    List<lectorModel> findByNombre(String nombre);

    // Buscar lectores cuyo nombre contenga una palabra
    @Query(value = "SELECT * FROM lector WHERE _01nombre LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<lectorModel> findByNombreLike(String nombre);

    // Buscar lectores por carrera
    @Query(value = "SELECT * FROM lector WHERE _08carrera = ?", nativeQuery = true)
    List<lectorModel> findByCarrera(String carrera);

    // Contar lectores por carrera
    @Query(value = "SELECT COUNT(*) FROM lector WHERE _08carrera = ?", nativeQuery = true)
    Long countByCarrera(String carrera);

    // Listar todos los lectores
    @Query(value = "SELECT * FROM lector", nativeQuery = true)
    List<lectorModel> listarLectores();
    @Query(value = """
    	    SELECT DISTINCT l.* 
    	    FROM lector l
    	    JOIN prestamo p ON l.id_lector = p._03id_lector
    	    JOIN esta_en ee ON p.id_prestamo = ee.id_prestamo
    	    JOIN libro lb ON ee.id_libro = lb.id_libro
    	    JOIN biblioteca b ON lb._08id_biblioteca = b.id_biblioteca
    	    WHERE b._07id_usuario = :idUsuario
    	    """, nativeQuery = true)
    	List<lectorModel> findLectoresPorAdmin(@Param("idUsuario") Long idUsuario);

}
