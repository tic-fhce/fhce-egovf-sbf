package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sbf.dao.tiene_eDao;
import com.fhce.sbf.dto.tiene_eDtoRequest;
import com.fhce.sbf.dto.tiene_eDtoResponse;
import com.fhce.sbf.model.tiene_eModel;
import com.fhce.sbf.service.tiene_eService;

@Service
public class tiene_eServiceImpl implements tiene_eService {

    @Autowired
    private tiene_eDao dao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public tiene_eDtoResponse save(tiene_eDtoRequest request) {
        tiene_eModel model = modelMapper.map(request, tiene_eModel.class);
        return modelMapper.map(dao.save(model), tiene_eDtoResponse.class);
    }

    @Override
    public List<tiene_eDtoResponse> findAll() {
        return dao.listarTodos().stream()
            .map(model -> modelMapper.map(model, tiene_eDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<tiene_eDtoResponse> buscarEstadosPorEjemplar(Long codigo) {
        return dao.buscarEstadosPorEjemplar(codigo).stream()
            .map(model -> modelMapper.map(model, tiene_eDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<tiene_eDtoResponse> buscarEjemplaresPorEstado(Long idEstado) {
        return dao.buscarEjemplaresPorEstado(idEstado).stream()
            .map(model -> modelMapper.map(model, tiene_eDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public Long contarEjemplaresPorEstado(Long idEstado) {
        return dao.contarEjemplaresPorEstado(idEstado);
    }

    @Override
    public Long existeRelacion(Long codigo, Long idEstado) {
        return dao.existeRelacion(codigo, idEstado);
    }

    @Override
    public List<Object[]> verEstadosDeEjemplar(Long codigo) {
        return dao.verEstadosDeEjemplar(codigo);
    }

    @Override
    public List<Object[]> listarEjemplaresConEstadoYLibro() {
        return dao.listarEjemplaresConEstadoYLibro();
    }

    @Override
    public Long contarEstadosDeEjemplar(Long codigo) {
        return dao.contarEstadosDeEjemplar(codigo);
    }

    @Override
    @Transactional
    public tiene_eDtoResponse delete(tiene_eDtoResponse response) {
        tiene_eModel model = dao.findById(response.getCodigo())
            .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + response.getCodigo()));
        dao.delete(model);
        return modelMapper.map(model, tiene_eDtoResponse.class);
    }
}
