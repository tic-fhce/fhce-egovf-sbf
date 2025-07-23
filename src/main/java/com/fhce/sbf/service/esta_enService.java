package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.esta_enDtoRequest;
import com.fhce.sbf.dto.esta_enDtoResponse;

public interface esta_enService {

    esta_enDtoResponse save(esta_enDtoRequest request);

    List<esta_enDtoResponse> findAll();

    List<esta_enDtoResponse> buscarPorLibro(Long id_libro);

    List<esta_enDtoResponse> buscarPorPrestamo(Long id_prestamo);

    Long contarLibrosEnPrestamo(Long id_prestamo);

    Long existeRelacion(Long id_libro, Long id_prestamo);

    esta_enDtoResponse delete(Long id_libro, Long id_prestamo);

    List<Object[]> listarRelaciones();
    
    esta_enDtoResponse edit(esta_enDtoRequest dto);

    List<esta_enDtoResponse> buscarPorEjemplar(Long idEjemplar);
    Long contarPorEjemplar(Long idEjemplar);
    List<Object[]> listarEjemplaresEnPrestamo(Long idPrestamo);
    esta_enDtoResponse editarEstaEn(esta_enDtoResponse res);


}
