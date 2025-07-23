package com.fhce.sbf.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhce.sbf.model.prestamoModel;

public interface prestamoDao extends JpaRepository<prestamoModel, Long> {

    // 1. Listar todos los préstamos
    @Query(value = "SELECT * FROM prestamo", nativeQuery = true)
    List<prestamoModel> listarTodos();

    // 2. Buscar préstamos por ID del lector
    @Query(value = "SELECT * FROM prestamo WHERE _03id_lector = ?", nativeQuery = true)
    List<prestamoModel> buscarPorLector(Long idLector);

    // 3. Buscar préstamos realizados en una fecha específica
    @Query(value = "SELECT * FROM prestamo WHERE _01fecha_prestamo = ?", nativeQuery = true)
    List<prestamoModel> buscarPorFechaPrestamo(LocalDate fecha);

    // 4. Buscar préstamos vencidos (fecha_devolucion antes de hoy)
    @Query(value = "SELECT * FROM prestamo WHERE _02fecha_devolucion < CURDATE()", nativeQuery = true)
    List<prestamoModel> buscarVencidos();

    // 5. Buscar préstamos activos (fecha_devolucion >= hoy)
    @Query(value = "SELECT * FROM prestamo WHERE _02fecha_devolucion >= CURDATE()", nativeQuery = true)
    List<prestamoModel> buscarActivos();

    // 6. Buscar préstamos entre dos fechas
    @Query(value = "SELECT * FROM prestamo WHERE _01fecha_prestamo BETWEEN ?1 AND ?2", nativeQuery = true)
    List<prestamoModel> buscarEntreFechas(LocalDate desde, LocalDate hasta);

    // 7. Contar préstamos realizados por un lector
    @Query(value = "SELECT COUNT(*) FROM prestamo WHERE _03id_lector = ?", nativeQuery = true)
    Long contarPorLector(Long idLector);

    // 8. Contar préstamos realizados en total
    @Query(value = "SELECT COUNT(*) FROM prestamo", nativeQuery = true)
    Long contarPrestamos();

    // 9. JOIN: Listar préstamos con nombre del lector
    @Query(value = """
        SELECT p.*, l._01nombre, l._02apellido_pat, l._03apellido_mat
        FROM prestamo p
        JOIN lector l ON p._03id_lector = l.id_lector
        """, nativeQuery = true)
    List<Object[]> listarPrestamosConNombreLector();

    // 10. JOIN: Contar préstamos por lector (agrupado por nombre completo)
    @Query(value = """
        SELECT CONCAT(l._01nombre, ' ', l._02apellido_pat, ' ', l._03apellido_mat) AS lector, COUNT(p.id_prestamo) AS total
        FROM prestamo p
        JOIN lector l ON p._03id_lector = l.id_lector
        GROUP BY l.id_lector
        ORDER BY total DESC
        """, nativeQuery = true)
    List<Object[]> contarPrestamosPorLector();

    // 11. Buscar préstamos donde falta la fecha de devolución (aún no devuelto)
    @Query(value = "SELECT * FROM prestamo WHERE _02fecha_devolucion IS NULL", nativeQuery = true)
    List<prestamoModel> buscarPrestamosSinDevolucion();

    // 12. Buscar préstamos de hoy
    @Query(value = "SELECT * FROM prestamo WHERE _01fecha_prestamo = CURDATE()", nativeQuery = true)
    List<prestamoModel> buscarPrestamosDeHoy();

    // 13. Contar préstamos hechos hoy
    @Query(value = "SELECT COUNT(*) FROM prestamo WHERE _01fecha_prestamo = CURDATE()", nativeQuery = true)
    Long contarPrestamosDeHoy();

    // 14. Contar préstamos vencidos (fecha devolución < hoy)
    @Query(value = "SELECT COUNT(*) FROM prestamo WHERE _02fecha_devolucion < CURDATE()", nativeQuery = true)
    Long contarPrestamosVencidos();

    // 15. Contar préstamos activos (aún no vencen)
    @Query(value = "SELECT COUNT(*) FROM prestamo WHERE _02fecha_devolucion >= CURDATE()", nativeQuery = true)
    Long contarPrestamosActivos();
    
    @Query(value = """
    	    SELECT p.*
    	    FROM prestamo p
    	    WHERE p._03id_lector = :idLector
    	""", nativeQuery = true)
    	List<prestamoModel> buscarPrestamosPorLector(@Param("idLector") Long idLector);

    	@Query(value = """
    	    SELECT p.*, b._01nombre AS nombre_biblioteca, b._02direccion AS direccion
    	    FROM prestamo p
    	    JOIN esta_en ee ON p.id_prestamo = ee.id_prestamo
    	    JOIN libro l ON ee.id_libro = l.id_libro
    	    JOIN biblioteca b ON l.id_biblioteca = b.id_biblioteca
    	    WHERE b._07id_usuario = :idUsuario
    	    ORDER BY b.id_biblioteca, p.id_prestamo
    	""", nativeQuery = true)
    	List<Object[]> buscarPrestamosPorUsuarioAdmin(@Param("idUsuario") Long idUsuario);


}
