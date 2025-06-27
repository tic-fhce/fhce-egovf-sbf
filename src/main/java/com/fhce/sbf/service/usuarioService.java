package com.fhce.sbf.service;

import java.util.List;

import com.fhce.sbf.dto.usuarioDtoRequest;
import com.fhce.sbf.dto.usuarioDtoResponse;

public interface usuarioService {
    List<usuarioDtoResponse> listarUsuarios();
    List<usuarioDtoResponse> buscarPorNombreContiene(String nombreParcial);
    List<usuarioDtoResponse> buscarPorRol(String rol);
    usuarioDtoResponse buscarPorNombreExacto(String nombre);
    Long contarPorRol(String rol);

    usuarioDtoResponse addUsuario(usuarioDtoRequest usuarioDtoRequest);
    usuarioDtoResponse editUsuario(usuarioDtoResponse usuarioDtoResponse);
    usuarioDtoResponse deleteUsuario(usuarioDtoResponse usuarioDtoResponse);
} 
