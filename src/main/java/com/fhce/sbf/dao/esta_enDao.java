package com.fhce.sbf.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.sbf.model.esta_enModel;

public interface esta_enDao extends JpaRepository<esta_enModel, Long> {

    List<esta_enModel> findByIdLibro(Long idLibro);

    List<esta_enModel> findByIdPrestamo(Long idPrestamo);

    boolean existsByIdLibroAndIdPrestamo(Long idLibro, Long idPrestamo);

    Long countByIdPrestamo(Long idPrestamo);

    void deleteByIdLibroAndIdPrestamo(Long idLibro, Long idPrestamo);

    @Query("SELECT e FROM esta_enModel e WHERE e.idLibro = :idLibro AND e.idPrestamo = :idPrestamo")
    Optional<esta_enModel> findByIdLibroAndIdPrestamo(@Param("idLibro") Long idLibro, @Param("idPrestamo") Long idPrestamo);

    @Query(value = "SELECT * FROM esta_en", nativeQuery = true)
    List<Object[]> listarRelaciones();

    @Query(value = """
        SELECT e.id_libro, l._01titulo AS titulo, e.id_prestamo, p._01estado AS estado
        FROM esta_en e
        JOIN libro l ON e.id_libro = l.id_libro
        JOIN prestamo p ON e.id_prestamo = p.id_prestamo
        """, nativeQuery = true)
    List<Object[]> listarDetallesRelacion();
}

