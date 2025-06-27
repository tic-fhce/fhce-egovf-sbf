package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.usuarioDao;
import com.fhce.sbf.dto.usuarioDtoRequest;
import com.fhce.sbf.dto.usuarioDtoResponse;
import com.fhce.sbf.model.usuarioModel;
import com.fhce.sbf.service.usuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usuarioServiceImpl implements usuarioService {

    private final usuarioDao usuarioDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<usuarioDtoResponse> listarUsuarios() {
        return usuarioDao.listarUsuarios().stream()
                .map(usuario -> modelMapper.map(usuario, usuarioDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<usuarioDtoResponse> buscarPorNombreContiene(String nombreParcial) {
        return usuarioDao.findByNombreContiene(nombreParcial).stream()
                .map(usuario -> modelMapper.map(usuario, usuarioDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public usuarioDtoResponse buscarPorNombreExacto(String nombre) {
        usuarioModel usuario = usuarioDao.findByNombreUsuario(nombre);
        return modelMapper.map(usuario, usuarioDtoResponse.class);
    }

    @Override
    @Transactional
    public List<usuarioDtoResponse> buscarPorRol(String rol) {
        return usuarioDao.findByRol(rol).stream()
                .map(usuario -> modelMapper.map(usuario, usuarioDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long contarPorRol(String rol) {
        return usuarioDao.countByRol(rol);
    }

    @Override
    @Transactional
    public usuarioDtoResponse addUsuario(usuarioDtoRequest usuarioDtoRequest) {
        usuarioModel usuario = new usuarioModel();
        usuario.setNombre_usuario(usuarioDtoRequest.getNombre_usuario());
        usuario.setContrasena(usuarioDtoRequest.getContrasena());
        usuario.setRol(usuarioDtoRequest.getRol());

        usuarioDao.save(usuario);
        return modelMapper.map(usuario, usuarioDtoResponse.class);
    }

    @Override
    @Transactional
    public usuarioDtoResponse editUsuario(usuarioDtoResponse usuarioDtoResponse) {
        usuarioModel usuario = new usuarioModel();
        usuario.setId_usuario(usuarioDtoResponse.getId_usuario());
        usuario.setNombre_usuario(usuarioDtoResponse.getNombre_usuario());
        usuario.setContrasena(usuarioDtoResponse.getContrasena());
        usuario.setRol(usuarioDtoResponse.getRol());

        usuarioDao.save(usuario);
        return modelMapper.map(usuario, usuarioDtoResponse.class);
    }

    @Override
    @Transactional
    public usuarioDtoResponse deleteUsuario(usuarioDtoResponse usuarioDtoResponse) {
        usuarioModel usuario = usuarioDao.findById(usuarioDtoResponse.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioDtoResponse.getId_usuario()));

        usuarioDao.delete(usuario);
        return modelMapper.map(usuario, usuarioDtoResponse.class);
    }
}
