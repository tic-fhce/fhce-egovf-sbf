package com.fhce.sbf.service;

import java.time.LocalDate;
import java.util.List;
import com.fhce.sbf.dto.prestamoDtoRequest;
import com.fhce.sbf.dto.prestamoDtoResponse;

public interface prestamoService {
    prestamoDtoResponse save(prestamoDtoRequest request);
    List<prestamoDtoResponse> findAll();
    List<prestamoDtoResponse> buscarPorLector(Long idLector);
    List<prestamoDtoResponse> buscarPorFechaPrestamo(LocalDate fecha);
    List<prestamoDtoResponse> buscarVencidos();
    List<prestamoDtoResponse> buscarActivos();
    List<prestamoDtoResponse> buscarEntreFechas(LocalDate desde, LocalDate hasta);
    Long contarPorLector(Long idLector);
    Long contarPrestamos();
    List<Object[]> listarPrestamosConNombreLector();
    List<Object[]> contarPrestamosPorLector();
    List<prestamoDtoResponse> buscarPrestamosSinDevolucion();
    List<prestamoDtoResponse> buscarPrestamosDeHoy();
    Long contarPrestamosDeHoy();
    Long contarPrestamosVencidos();
    Long contarPrestamosActivos();
    prestamoDtoResponse delete(prestamoDtoResponse prestamo);
}