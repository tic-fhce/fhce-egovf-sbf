package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sbf.dao.esta_enDao;
import com.fhce.sbf.dto.esta_enDtoRequest;
import com.fhce.sbf.dto.esta_enDtoResponse;
import com.fhce.sbf.model.esta_enModel;
import com.fhce.sbf.service.esta_enService;

@Service
public class esta_enServiceImpl implements esta_enService {

    @Autowired
    private esta_enDao dao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public esta_enDtoResponse save(esta_enDtoRequest request) {
        esta_enModel model = modelMapper.map(request, esta_enModel.class);
        return modelMapper.map(dao.save(model), esta_enDtoResponse.class);
    }

    @Override
    public List<esta_enDtoResponse> findAll() {
        return dao.findAll().stream()
            .map(model -> modelMapper.map(model, esta_enDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<esta_enDtoResponse> buscarPorLibro(Long idLibro) {
        return dao.findByIdLibro(idLibro).stream()
            .map(model -> modelMapper.map(model, esta_enDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<esta_enDtoResponse> buscarPorPrestamo(Long idPrestamo) {
        return dao.findByIdPrestamo(idPrestamo).stream()
            .map(model -> modelMapper.map(model, esta_enDtoResponse.class))
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
    @Transactional
    public esta_enDtoResponse delete(Long idLibro, Long idPrestamo) {
        Optional<esta_enModel> opt = dao.findByIdLibroAndIdPrestamo(idLibro, idPrestamo);

        if (opt.isPresent()) {
            dao.deleteByIdLibroAndIdPrestamo(idLibro, idPrestamo);
            return modelMapper.map(opt.get(), esta_enDtoResponse.class);
        } else {
            throw new RuntimeException("No se encontró la relación a eliminar.");
        }
    }

    @Override
    public List<Object[]> listarRelaciones() {
        return dao.listarRelaciones();
    }

    @Override
    @Transactional
    public esta_enDtoResponse edit(esta_enDtoRequest dto) {
        Optional<esta_enModel> opt = dao.findByIdLibroAndIdPrestamoAndIdEjemplar(
            dto.getIdLibro(), dto.getIdPrestamo(), dto.getIdEjemplar());

        if (!opt.isPresent()) {
            throw new RuntimeException("No se encontró la relación con id_libro=" + dto.getIdLibro()
                + ", id_prestamo=" + dto.getIdPrestamo()
                + ", id_ejemplar=" + dto.getIdEjemplar());
        }

        esta_enModel existente = opt.get();
        existente.setIdLibro(dto.getIdLibro());
        existente.setIdPrestamo(dto.getIdPrestamo());
        existente.setIdEjemplar(dto.getIdEjemplar());

        esta_enModel actualizado = dao.save(existente);

        return modelMapper.map(actualizado, esta_enDtoResponse.class);
    }

    @Override
    public List<esta_enDtoResponse> buscarPorEjemplar(Long idEjemplar) {
        return dao.findByIdEjemplar(idEjemplar).stream()
            .map(model -> modelMapper.map(model, esta_enDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Override
    public Long contarPorEjemplar(Long idEjemplar) {
        return dao.countByEjemplar(idEjemplar);
    }

    @Override
    public List<Object[]> listarEjemplaresEnPrestamo(Long idPrestamo) {
        return dao.listarEjemplaresEnPrestamo(idPrestamo);
    }
    @Override
    @Transactional
    public esta_enDtoResponse editarEstaEn(esta_enDtoResponse res) {
        Optional<esta_enModel> opt = dao.findByIdPrestamo(res.getIdPrestamo());

        if (!opt.isPresent()) {
            throw new RuntimeException("No se encontró ninguna relación con id_prestamo = " + res.getIdPrestamo());
        }

        esta_enModel existente = opt.get();

        existente.setIdLibro(res.getIdLibro());
        existente.setIdEjemplar(res.getIdEjemplar());

        dao.save(existente);
        return modelMapper.map(existente, esta_enDtoResponse.class);
    }



}
