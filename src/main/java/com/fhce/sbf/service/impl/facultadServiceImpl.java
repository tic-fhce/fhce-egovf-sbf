package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.facultadDao;
import com.fhce.sbf.dto.facultadDtoRequest;
import com.fhce.sbf.dto.facultadDtoResponse;
import com.fhce.sbf.model.facultadModel;
import com.fhce.sbf.service.facultadService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class facultadServiceImpl implements facultadService {

    private final facultadDao facultadDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<facultadDtoResponse> listar_facultades() {
        return facultadDao.listarFacultades().stream()
                .map(f -> modelMapper.map(f, facultadDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public facultadDtoResponse addFacultad(facultadDtoRequest request) {
        facultadModel model = new facultadModel();
        model.setNombre(request.getNombre());
        facultadDao.save(model);
        return modelMapper.map(model, facultadDtoResponse.class);
    }

    @Override
    @Transactional
    public facultadDtoResponse editFacultad(facultadDtoResponse response) {
        facultadModel model = new facultadModel();
        model.setId_facultad(response.getId_facultad());
        model.setNombre(response.getNombre());
        facultadDao.save(model);
        return modelMapper.map(model, facultadDtoResponse.class);
    }

    @Override
    @Transactional
    public facultadDtoResponse buscarPorNombre(String nombre) {
        facultadModel model = facultadDao.findByNombre(nombre);
        return model != null ? modelMapper.map(model, facultadDtoResponse.class) : null;
    }

    @Override
    @Transactional
    public List<facultadDtoResponse> buscarPorNombreContiene(String texto) {
        return facultadDao.findByNombreContiene(texto).stream()
                .map(f -> modelMapper.map(f, facultadDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long contarFacultades() {
        return facultadDao.contarFacultades();
    }

    @Override
    @Transactional
    public List<String> listarNombres() {
        return facultadDao.listarNombres();
    }
    @Transactional
    public facultadDtoResponse deleteFacultad(facultadDtoResponse facultadDtoResponse) {
        facultadModel facultad = facultadDao.findById(facultadDtoResponse.getId_facultad())
            .orElseThrow(() -> new RuntimeException("Facultad no encontrada con ID: " + facultadDtoResponse.getId_facultad()));

        // Aquí podrías eliminar unidades asociadas si quieres evitar claves foráneas rotas

        facultadDao.delete(facultad);

        return modelMapper.map(facultad, facultadDtoResponse.class);
    }

}
