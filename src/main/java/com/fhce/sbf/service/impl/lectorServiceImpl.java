package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fhce.sbf.dao.lectorDao;
import com.fhce.sbf.dto.lectorDtoRequest;
import com.fhce.sbf.dto.lectorDtoResponse;
import com.fhce.sbf.model.lectorModel;
import com.fhce.sbf.service.lectorService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class lectorServiceImpl implements lectorService {

    private final lectorDao lectorDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<lectorDtoResponse> listarLectores() {
        return lectorDao.listarLectores().stream()
                .map(lector -> modelMapper.map(lector, lectorDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public lectorDtoResponse addLector(lectorDtoRequest request) {
        lectorModel lector = modelMapper.map(request, lectorModel.class);
        lectorDao.save(lector);
        return modelMapper.map(lector, lectorDtoResponse.class);
    }

    @Override
    @Transactional
    public lectorDtoResponse editLector(lectorDtoResponse response) {
        lectorModel lector = modelMapper.map(response, lectorModel.class);
        lectorDao.save(lector);
        return modelMapper.map(lector, lectorDtoResponse.class);
    }

    @Override
    @Transactional
    public lectorDtoResponse deleteLector(lectorDtoResponse response) {
        lectorModel lector = lectorDao.findById(response.getId_lector())
                .orElseThrow(() -> new RuntimeException("Lector no encontrado con ID: " + response.getId_lector()));
        lectorDao.delete(lector);
        return modelMapper.map(lector, lectorDtoResponse.class);
    }

    @Override
    public lectorDtoResponse findByCi(int ci) {
        return modelMapper.map(lectorDao.findByCi(ci), lectorDtoResponse.class);
    }

    @Override
    public lectorDtoResponse findByRu(int ru) {
        return modelMapper.map(lectorDao.findByRu(ru), lectorDtoResponse.class);
    }

    @Override
    public List<lectorDtoResponse> findByNombre(String nombre) {
        return lectorDao.findByNombre(nombre).stream()
                .map(lector -> modelMapper.map(lector, lectorDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<lectorDtoResponse> findByNombreLike(String nombre) {
        return lectorDao.findByNombreLike(nombre).stream()
                .map(lector -> modelMapper.map(lector, lectorDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<lectorDtoResponse> findByCarrera(String carrera) {
        return lectorDao.findByCarrera(carrera).stream()
                .map(lector -> modelMapper.map(lector, lectorDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long countByCarrera(String carrera) {
        return lectorDao.countByCarrera(carrera);
    }
    
    @Override
    public List<lectorModel> obtenerLectoresPorAdmin(Long idUsuario) {
        return lectorDao.findLectoresPorAdmin(idUsuario);
    }

}