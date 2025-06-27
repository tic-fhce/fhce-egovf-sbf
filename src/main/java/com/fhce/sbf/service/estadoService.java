package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.estadoDtoRequest;
import com.fhce.sbf.dto.estadoDtoResponse;

public interface estadoService {
    List<estadoDtoResponse> listarTodos();
    estadoDtoResponse buscarPorTipo(String tipo);
    List<estadoDtoResponse> buscarPorTipoContiene(String tipo);
    estadoDtoResponse buscarPorId(Long id);
    List<String> listarNombresEstados();
    Long contarEstados();
    estadoDtoResponse agregarEstado(estadoDtoRequest estadoDtoRequest);
    estadoDtoResponse editarEstado(estadoDtoResponse estadoDtoResponse);
    estadoDtoResponse eliminarEstado(estadoDtoResponse estadoDtoResponse);
}