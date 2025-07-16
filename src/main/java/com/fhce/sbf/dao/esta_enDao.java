package com.fhce.sbf.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.sbf.model.esta_enModel;

public interface esta_enDao extends JpaRepository<esta_enModel, Long> {

    // Buscar todos los registros por id de préstamo
    List<esta_enModel> findById_prestamo(Long id_prestamo);

    // Buscar todos los registros por id de libro
    List<esta_enModel> findById_libro(Long id_libro);

    // Contar cuántos libros hay en un préstamo específico
    @Query("SELECT COUNT(e) FROM esta_enModel e WHERE e.id_prestamo = :idPrestamo")
    Long contarLibrosEnPrestamo(@Param("idPrestamo") Long idPrestamo);

    // Verificar si un libro está asociado a un préstamo específico
    @Query("SELECT COUNT(e) FROM esta_enModel e WHERE e.id_libro = :idLibro AND e.id_prestamo = :idPrestamo")
    Long existeRelacion(@Param("idLibro") Long idLibro, @Param("idPrestamo") Long idPrestamo);

    // Eliminar una relación específica (usando combinación de IDs)
    void deleteById_libroAndId_prestamo(Long id_libro, Long id_prestamo);

    // Obtener todos los libros con su id_prestamo agrupados
    @Query("SELECT e.id_libro, e.id_prestamo FROM esta_enModel e")
    List<Object[]> listarRelaciones();
}
