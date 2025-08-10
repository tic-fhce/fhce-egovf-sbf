package com.fhce.sbf.service;

import java.util.List;
import com.fhce.sbf.dto.lectorDtoRequest;
import com.fhce.sbf.dto.lectorDtoResponse;
import com.fhce.sbf.model.lectorModel;

public interface lectorService {
    List<lectorDtoResponse> listarLectores();
    lectorDtoResponse addLector(lectorDtoRequest request);
    lectorDtoResponse editLector(lectorDtoResponse response);
    lectorDtoResponse deleteLector(lectorDtoResponse response);

    lectorDtoResponse findByCi(int ci);
    lectorDtoResponse findByRu(int ru);
    List<lectorDtoResponse> findByNombre(String nombre);
    List<lectorDtoResponse> findByNombreLike(String nombre);
    List<lectorDtoResponse> findByCarrera(String carrera);
    Long countByCarrera(String carrera);
    List<lectorModel> obtenerLectoresPorAdmin(Long idUsuario);

}