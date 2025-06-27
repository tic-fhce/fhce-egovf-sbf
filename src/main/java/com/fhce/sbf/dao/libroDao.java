package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.libroModel;

public interface libroDao extends JpaRepository<libroModel, Long> {

    // Buscar libro por título exacto
    @Query(value = "SELECT * FROM libro WHERE _01titulo = ?", nativeQuery = true)
    libroModel findByTitulo(String titulo);

    // Buscar libros por coincidencia parcial de título
    @Query(value = "SELECT * FROM libro WHERE _01titulo LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<libroModel> findByTituloLike(String titulo);

    // Buscar libros por autor
    @Query(value = "SELECT * FROM libro WHERE _02autor = ?", nativeQuery = true)
    List<libroModel> findByAutor(String autor);

    // Buscar libros por idioma
    @Query(value = "SELECT * FROM libro WHERE _04idioma = ?", nativeQuery = true)
    List<libroModel> findByIdioma(String idioma);

    // Buscar libros por año
    @Query(value = "SELECT * FROM libro WHERE _03anio = ?", nativeQuery = true)
    List<libroModel> findByAnio(int anio);

    // Buscar libros por biblioteca
    @Query(value = "SELECT * FROM libro WHERE _09id_biblioteca = ?", nativeQuery = true)
    List<libroModel> findByBiblioteca(Long idBiblioteca);

    // Buscar libros por usuario (quien los cargó)
    @Query(value = "SELECT * FROM libro WHERE _08id_usuario = ?", nativeQuery = true)
    List<libroModel> findByUsuario(Long idUsuario);

    // Contar libros por biblioteca
    @Query(value = "SELECT COUNT(*) FROM libro WHERE _09id_biblioteca = ?", nativeQuery = true)
    Long countByBiblioteca(Long idBiblioteca);

    // Listar todos los libros
    @Query(value = "SELECT * FROM libro", nativeQuery = true)
    List<libroModel> listarLibros();

    // 🔗 JOIN libro + biblioteca → para ver el nombre de la biblioteca
    @Query(value = """
        SELECT l.*, b._01nombre AS nombre_biblioteca 
        FROM libro l
        JOIN biblioteca b ON l._09id_biblioteca = b.id_biblioteca
        """, nativeQuery = true)
    List<Object[]> listarLibrosConBiblioteca();

    // 🔗 JOIN libro + usuario → para ver el nombre del usuario que cargó el libro
    @Query(value = """
        SELECT l.*, u._01nombre_usuario AS nombre_usuario 
        FROM libro l
        JOIN usuario u ON l._08id_usuario = u.id_usuario
        """, nativeQuery = true)
    List<Object[]> listarLibrosConUsuario();

    // 🔗 JOIN libro + biblioteca + usuario → para ver todo junto (libro + biblioteca + usuario)
    @Query(value = """
        SELECT l.*, b._01nombre AS nombre_biblioteca, u._01nombre_usuario AS nombre_usuario 
        FROM libro l
        JOIN biblioteca b ON l._09id_biblioteca = b.id_biblioteca
        JOIN usuario u ON l._08id_usuario = u.id_usuario
        """, nativeQuery = true)
    List<Object[]> listarLibrosConBibliotecaYUsuario();
}
