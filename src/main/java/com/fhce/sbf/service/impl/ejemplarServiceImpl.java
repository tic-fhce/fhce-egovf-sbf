package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fhce.sbf.dao.ejemplarDao;
import com.fhce.sbf.dto.ejemplarDtoRequest;
import com.fhce.sbf.dto.ejemplarDtoResponse;
import com.fhce.sbf.model.ejemplarModel;
import com.fhce.sbf.service.ejemplarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ejemplarServiceImpl implements ejemplarService {

    private final ejemplarDao ejemplarDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<ejemplarDtoResponse> listarEjemplares() {
        return ejemplarDao.listarTodos().stream()
            .map(e -> modelMapper.map(e, ejemplarDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ejemplarDtoResponse addEjemplar(ejemplarDtoRequest dto) {
        ejemplarModel model = modelMapper.map(dto, ejemplarModel.class);
        ejemplarDao.save(model);
        return modelMapper.map(model, ejemplarDtoResponse.class);
    }

    @Override
    @Transactional
    public ejemplarDtoResponse editEjemplar(ejemplarDtoResponse dto) {
        ejemplarModel model = modelMapper.map(dto, ejemplarModel.class);
        ejemplarDao.save(model);
        return modelMapper.map(model, ejemplarDtoResponse.class);
    }

    @Override
    @Transactional
    public ejemplarDtoResponse deleteEjemplar(ejemplarDtoResponse dto) {
        ejemplarModel model = ejemplarDao.findById(dto.getCodigo())
            .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));
        ejemplarDao.delete(model);
        return modelMapper.map(model, ejemplarDtoResponse.class);
    }

    @Override
    public List<ejemplarDtoResponse> findByEstado(String estado) {
        return ejemplarDao.findByEstado(estado).stream()
            .map(e -> modelMapper.map(e, ejemplarDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<ejemplarDtoResponse> findByLibro(Long idLibro) {
        return ejemplarDao.findByLibro(idLibro).stream()
            .map(e -> modelMapper.map(e, ejemplarDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<String> listarDirecciones() {
        return ejemplarDao.listarDirecciones();
    }

    @Override
    public List<String> listarPortadas() {
        return ejemplarDao.listarPortadas();
    }
}