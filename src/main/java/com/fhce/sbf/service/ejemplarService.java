package com.fhce.sbf.service;

import java.util.List;
import com.fhce.sbf.dto.ejemplarDtoRequest;
import com.fhce.sbf.dto.ejemplarDtoResponse;

public interface ejemplarService {
    List<ejemplarDtoResponse> listarEjemplares();
    ejemplarDtoResponse addEjemplar(ejemplarDtoRequest dto);
    ejemplarDtoResponse editEjemplar(ejemplarDtoResponse dto);
    ejemplarDtoResponse deleteEjemplar(ejemplarDtoResponse dto);
    List<ejemplarDtoResponse> findByEstado(String estado);
    List<ejemplarDtoResponse> findByLibro(Long idLibro);
    List<String> listarDirecciones();
    List<String> listarPortadas();
} 