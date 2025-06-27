package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.unidadDtoRequest;
import com.fhce.sbf.dto.unidadDtoResponse;

public interface unidadService {

    List<unidadDtoResponse> listarUnidades();
    unidadDtoResponse addUnidad(unidadDtoRequest request);
    unidadDtoResponse editUnidad(unidadDtoResponse response);

    unidadDtoResponse buscarPorNombre(String nombre);
    List<unidadDtoResponse> buscarPorNombreLike(String texto);
    List<unidadDtoResponse> buscarPorTipo(String tipo);
    List<unidadDtoResponse> buscarPorFacultad(Long idFacultad);
    Long contarPorTipo(String tipo);
    List<Object[]> listarUnidadesConFacultad();
    unidadDtoResponse deleteUnidad(unidadDtoResponse unidadDtoResponse);

}
