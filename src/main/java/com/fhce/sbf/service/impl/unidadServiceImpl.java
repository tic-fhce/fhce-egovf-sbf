package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.unidadDao;
import com.fhce.sbf.dto.unidadDtoRequest;
import com.fhce.sbf.dto.unidadDtoResponse;
import com.fhce.sbf.model.unidadModel;
import com.fhce.sbf.service.unidadService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class unidadServiceImpl implements unidadService {

    private final unidadDao unidadDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<unidadDtoResponse> listarUnidades() {
        return unidadDao.listarUnidades().stream()
                .map(u -> modelMapper.map(u, unidadDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public unidadDtoResponse addUnidad(unidadDtoRequest request) {
        unidadModel model = new unidadModel();
        model.setNombre(request.getNombre());
        model.setTipo(request.getTipo());
        model.setId_facultad(request.getId_facultad());
        unidadDao.save(model);
        return modelMapper.map(model, unidadDtoResponse.class);
    }

    @Override
    @Transactional
    public unidadDtoResponse editUnidad(unidadDtoResponse response) {
        unidadModel model = new unidadModel();
        model.setId_unidad(response.getId_unidad());
        model.setNombre(response.getNombre());
        model.setTipo(response.getTipo());
        model.setId_facultad(response.getId_facultad());
        unidadDao.save(model);
        return modelMapper.map(model, unidadDtoResponse.class);
    }

    @Override
    @Transactional
    public unidadDtoResponse buscarPorNombre(String nombre) {
        unidadModel model = unidadDao.findByNombre(nombre);
        return model != null ? modelMapper.map(model, unidadDtoResponse.class) : null;
    }

    @Override
    @Transactional
    public List<unidadDtoResponse> buscarPorNombreLike(String texto) {
        return unidadDao.findByNombreLike(texto).stream()
                .map(u -> modelMapper.map(u, unidadDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<unidadDtoResponse> buscarPorTipo(String tipo) {
        return unidadDao.findByTipo(tipo).stream()
                .map(u -> modelMapper.map(u, unidadDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<unidadDtoResponse> buscarPorFacultad(Long idFacultad) {
        return unidadDao.findByFacultad(idFacultad).stream()
                .map(u -> modelMapper.map(u, unidadDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long contarPorTipo(String tipo) {
        return unidadDao.countByTipo(tipo);
    }

    @Override
    @Transactional
    public List<Object[]> listarUnidadesConFacultad() {
        return unidadDao.listarUnidadesConFacultad();
    }
    @Transactional
    public unidadDtoResponse deleteUnidad(unidadDtoResponse unidadDtoResponse) {
        unidadModel unidad = unidadDao.findById(unidadDtoResponse.getId_unidad())
            .orElseThrow(() -> new RuntimeException("Unidad no encontrada con ID: " + unidadDtoResponse.getId_unidad()));

        unidadDao.delete(unidad);

        return modelMapper.map(unidad, unidadDtoResponse.class);
    }

}
