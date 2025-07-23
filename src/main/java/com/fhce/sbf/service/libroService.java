package com.fhce.sbf.service;

import java.util.List;
import com.fhce.sbf.dto.libroDtoRequest;
import com.fhce.sbf.dto.libroDtoResponse;

public interface libroService {
    List<libroDtoResponse> listarLibros();
    libroDtoResponse addLibro(libroDtoRequest dto);
    libroDtoResponse editLibro(libroDtoResponse dto);
    libroDtoResponse deleteLibro(libroDtoResponse dto);
    List<libroDtoResponse> buscarPorAutor(String autor);
    List<libroDtoResponse> buscarPorIdioma(String idioma);
    List<libroDtoResponse> buscarPorBiblioteca(Long id);
    List<libroDtoResponse> buscarPorUsuario(Long id);
    List<libroDtoResponse> buscarPorTitulo(String titulo);
    List<libroDtoResponse> buscarPorAnio(int anio);
    List<Object[]> obtenerBibliotecasConLibrosPorUsuario(Long idUsuario);

}