package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.esta_enDao;
import com.fhce.sbf.dto.esta_enDtoRequest;
import com.fhce.sbf.dto.esta_enDtoResponse;
import com.fhce.sbf.model.esta_enModel;
import com.fhce.sbf.service.esta_enService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class esta_enServiceImpl implements esta_enService {

    private final esta_enDao dao;

    @Override
    public esta_enDtoResponse save(esta_enDtoRequest request) {
        esta_enModel model = new esta_enModel();
        model.setIdLibro(request.getIdLibro());
        model.setIdPrestamo(request.getIdPrestamo());

        esta_enModel saved = dao.save(model);
        return new esta_enDtoResponse(saved.getIdEstaEn(), saved.getIdLibro(), saved.getIdPrestamo());
    }

    @Override
    public List<esta_enDtoResponse> findAll() {
        return dao.findAll().stream()
            .map(m -> new esta_enDtoResponse(m.getIdEstaEn(), m.getIdLibro(), m.getIdPrestamo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<esta_enDtoResponse> buscarPorLibro(Long idLibro) {
        return dao.findByIdLibro(idLibro).stream()
            .map(m -> new esta_enDtoResponse(m.getIdEstaEn(), m.getIdLibro(), m.getIdPrestamo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<esta_enDtoResponse> buscarPorPrestamo(Long idPrestamo) {
        return dao.findByIdPrestamo(idPrestamo).stream()
            .map(m -> new esta_enDtoResponse(m.getIdEstaEn(), m.getIdLibro(), m.getIdPrestamo()))
            .collect(Collectors.toList());
    }

    @Override
    public Long contarLibrosEnPrestamo(Long idPrestamo) {
        return dao.countByIdPrestamo(idPrestamo);
    }

    @Override
    public Long existeRelacion(Long idLibro, Long idPrestamo) {
        return dao.existsByIdLibroAndIdPrestamo(idLibro, idPrestamo) ? 1L : 0L;
    }

    @Override
    public esta_enDtoResponse delete(Long idLibro, Long idPrestamo) {
        Optional<esta_enModel> opt = dao.findByIdLibroAndIdPrestamo(idLibro, idPrestamo);

        if (opt.isPresent()) {
            dao.deleteByIdLibroAndIdPrestamo(idLibro, idPrestamo);
            esta_enModel e = opt.get();
            return new esta_enDtoResponse(e.getIdEstaEn(), e.getIdLibro(), e.getIdPrestamo());
        } else {
            throw new RuntimeException("No se encontró la relación a eliminar.");
        }
    }

    @Override
    public List<Object[]> listarRelaciones() {
        return dao.listarRelaciones();
    }
}
