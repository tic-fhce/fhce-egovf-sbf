package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.esta_enDtoRequest;
import com.fhce.sbf.dto.esta_enDtoResponse;

public interface esta_enService {

    esta_enDtoResponse save(esta_enDtoRequest request);

    List<esta_enDtoResponse> findAll();

    List<esta_enDtoResponse> buscarPorLibro(Long idLibro);

    List<esta_enDtoResponse> buscarPorPrestamo(Long idPrestamo);

    Long contarLibrosEnPrestamo(Long idPrestamo);

    Long existeRelacion(Long idLibro, Long idPrestamo);

    esta_enDtoResponse delete(Long idLibro, Long idPrestamo);

    List<Object[]> listarRelaciones();
}
