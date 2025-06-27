package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.estadoDao;
import com.fhce.sbf.dto.estadoDtoRequest;
import com.fhce.sbf.dto.estadoDtoResponse;
import com.fhce.sbf.model.estadoModel;
import com.fhce.sbf.service.estadoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class estadoServiceImpl implements estadoService {
    private final estadoDao estadoDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<estadoDtoResponse> listarTodos() {
        return estadoDao.listarTodos().stream()
            .map(e -> modelMapper.map(e, estadoDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public estadoDtoResponse buscarPorTipo(String tipo) {
        return modelMapper.map(estadoDao.findByTipo(tipo), estadoDtoResponse.class);
    }

    @Override
    @Transactional
    public List<estadoDtoResponse> buscarPorTipoContiene(String tipo) {
        return estadoDao.findByTipoContiene(tipo).stream()
            .map(e -> modelMapper.map(e, estadoDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public estadoDtoResponse buscarPorId(Long id) {
        return modelMapper.map(estadoDao.findByIdNativo(id), estadoDtoResponse.class);
    }

    @Override
    @Transactional
    public List<String> listarNombresEstados() {
        return estadoDao.listarNombresEstados();
    }

    @Override
    @Transactional
    public Long contarEstados() {
        return estadoDao.contarEstados();
    }

    @Override
    @Transactional
    public estadoDtoResponse agregarEstado(estadoDtoRequest req) {
        estadoModel e = new estadoModel();
        e.setTipo(req.getTipo());
        estadoDao.save(e);
        return modelMapper.map(e, estadoDtoResponse.class);
    }

    @Override
    @Transactional
    public estadoDtoResponse editarEstado(estadoDtoResponse res) {
        estadoModel e = new estadoModel();
        e.setId_estado(res.getId_estado());
        e.setTipo(res.getTipo());
        estadoDao.save(e);
        return modelMapper.map(e, estadoDtoResponse.class);
    }

    @Override
    @Transactional
    public estadoDtoResponse eliminarEstado(estadoDtoResponse res) {
        estadoModel e = estadoDao.findById(res.getId_estado())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + res.getId_estado()));
        estadoDao.delete(e);
        return modelMapper.map(e, estadoDtoResponse.class);
    }
}