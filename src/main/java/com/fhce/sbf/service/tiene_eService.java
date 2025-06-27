package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.tiene_eDtoRequest;
import com.fhce.sbf.dto.tiene_eDtoResponse;

public interface tiene_eService {

    tiene_eDtoResponse save(tiene_eDtoRequest request);

    List<tiene_eDtoResponse> findAll();

    List<tiene_eDtoResponse> buscarEstadosPorEjemplar(Long codigo);

    List<tiene_eDtoResponse> buscarEjemplaresPorEstado(Long idEstado);

    Long contarEjemplaresPorEstado(Long idEstado);

    Long existeRelacion(Long codigo, Long idEstado);

    List<Object[]> verEstadosDeEjemplar(Long codigo);

    List<Object[]> listarEjemplaresConEstadoYLibro();

    Long contarEstadosDeEjemplar(Long codigo);

    tiene_eDtoResponse delete(tiene_eDtoResponse response);
}
