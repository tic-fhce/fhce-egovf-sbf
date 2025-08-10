package com.fhce.sbf.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.sbf.model.bibliotecaModel;
import com.fhce.sbf.model.libroModel;

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
    
    //Buscar biblioteca por id_usuario
    @Query(value = "SELECT * FROM biblioteca WHERE _07id_usuario = ?", nativeQuery = true)
    List<bibliotecaModel> findByUsuario(Long idUsuario);

    @Query(value = """
    	    SELECT * 
    	    FROM libro l
    	    WHERE l._09id_biblioteca IN (
    	        SELECT b.id_biblioteca 
    	        FROM biblioteca b 
    	        WHERE b._07id_usuario = :idUsuarioAdmin
    	    )
    	    """, nativeQuery = true)
    	List<libroModel> findLibrosByAdmin(@Param("idUsuarioAdmin") Long idUsuarioAdmin);

    	Optional<bibliotecaModel> findById(Long idBiblioteca);
    	@Query(value = "SELECT * FROM biblioteca WHERE _07id_usuario = ?1", nativeQuery = true)
        List<bibliotecaModel> findByIdUsuario(Long idUsuario);

        @Query(value = "SELECT COUNT(*) FROM biblioteca WHERE _07id_usuario = ?1", nativeQuery = true)
        Long countByIdUsuario(Long idUsuario);
}
