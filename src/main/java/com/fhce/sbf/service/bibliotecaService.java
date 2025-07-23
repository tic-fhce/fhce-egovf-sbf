package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.bibliotecaDtoRequest;
import com.fhce.sbf.dto.bibliotecaDtoResponse;
import com.fhce.sbf.model.bibliotecaModel;

public interface bibliotecaService {

    List<bibliotecaDtoResponse> listarBibliotecas();
    bibliotecaDtoResponse addBiblioteca(bibliotecaDtoRequest request);
    bibliotecaDtoResponse editBiblioteca(bibliotecaDtoResponse response);

    List<bibliotecaDtoResponse> buscarPorNombre(String nombre);
    List<bibliotecaDtoResponse> buscarPorFacultad(Long idFacultad);
    List<bibliotecaDtoResponse> buscarPorHorario(String horario);
    Long contarPorFacultad(Long idFacultad);
    bibliotecaDtoResponse deleteBiblioteca(bibliotecaDtoResponse bibliotecaDtoResponse);
    List<bibliotecaModel> buscarPorUsuario(Long idUsuario);

}
