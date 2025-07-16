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
        model.setId_libro(request.getId_libro());
        model.setId_prestamo(request.getId_prestamo());

        esta_enModel saved = dao.save(model);
        return new esta_enDtoResponse(saved.getId_esta_en(), saved.getId_libro(), saved.getId_prestamo());
    }

    @Override
    public List<esta_enDtoResponse> findAll() {
        return dao.findAll().stream()
            .map(m -> new esta_enDtoResponse(m.getId_esta_en(), m.getId_libro(), m.getId_prestamo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<esta_enDtoResponse> buscarPorLibro(Long idLibro) {
        return dao.findById_libro(idLibro).stream()
            .map(m -> new esta_enDtoResponse(m.getId_esta_en(), m.getId_libro(), m.getId_prestamo()))
            .collect(Collectors.toList());
    }

    @Override
    public List<esta_enDtoResponse> buscarPorPrestamo(Long idPrestamo) {
        return dao.findById_prestamo(idPrestamo).stream()
            .map(m -> new esta_enDtoResponse(m.getId_esta_en(), m.getId_libro(), m.getId_prestamo()))
            .collect(Collectors.toList());
    }

    @Override
    public Long contarLibrosEnPrestamo(Long idPrestamo) {
        return dao.contarLibrosEnPrestamo(idPrestamo);
    }

    @Override
    public Long existeRelacion(Long idLibro, Long idPrestamo) {
        return dao.existeRelacion(idLibro, idPrestamo);
    }

    @Override
    public esta_enDtoResponse delete(Long idLibro, Long idPrestamo) {
        Optional<esta_enModel> opt = dao.findAll().stream()
            .filter(e -> e.getId_libro().equals(idLibro) && e.getId_prestamo().equals(idPrestamo))
            .findFirst();

        if (opt.isPresent()) {
            dao.deleteById_libroAndId_prestamo(idLibro, idPrestamo);
            esta_enModel e = opt.get();
            return new esta_enDtoResponse(e.getId_esta_en(), e.getId_libro(), e.getId_prestamo());
        } else {
            throw new RuntimeException("No se encontró la relación a eliminar.");
        }
    }

    @Override
    public List<Object[]> listarRelaciones() {
        return dao.listarRelaciones();
    }
}
