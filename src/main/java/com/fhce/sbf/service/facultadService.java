package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.facultadDtoRequest;
import com.fhce.sbf.dto.facultadDtoResponse;

public interface facultadService {
    
    List<facultadDtoResponse> listar_facultades();
    facultadDtoResponse addFacultad(facultadDtoRequest request);
    facultadDtoResponse editFacultad(facultadDtoResponse response);

    facultadDtoResponse buscarPorNombre(String nombre);
    List<facultadDtoResponse> buscarPorNombreContiene(String texto);
    Long contarFacultades();
    List<String> listarNombres();
    facultadDtoResponse deleteFacultad(facultadDtoResponse facultadDtoResponse);

}
