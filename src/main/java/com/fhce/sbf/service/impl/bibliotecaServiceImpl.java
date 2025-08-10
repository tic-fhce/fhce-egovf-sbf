package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.bibliotecaDao;
import com.fhce.sbf.dto.bibliotecaDtoRequest;
import com.fhce.sbf.dto.bibliotecaDtoResponse;
import com.fhce.sbf.model.bibliotecaModel;
import com.fhce.sbf.service.bibliotecaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class bibliotecaServiceImpl implements bibliotecaService {

    private final bibliotecaDao bibliotecaDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<bibliotecaDtoResponse> listarBibliotecas() {
        return bibliotecaDao.findAll().stream()
                .map(b -> modelMapper.map(b, bibliotecaDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public bibliotecaDtoResponse addBiblioteca(bibliotecaDtoRequest request) {
        bibliotecaModel model = new bibliotecaModel();
        model.setNombre(request.getNombre());
        model.setDireccion(request.getDireccion());
        model.setLatitud(request.getLatitud());
        model.setLongitud(request.getLongitud());
        model.setHorario_atencion(request.getHorario_atencion());
        model.setId_facultad(request.getId_facultad());
        bibliotecaDao.save(model);
        return modelMapper.map(model, bibliotecaDtoResponse.class);
    }

    @Override
    @Transactional
    public bibliotecaDtoResponse editBiblioteca(bibliotecaDtoResponse response) {
        bibliotecaModel model = new bibliotecaModel();
        model.setId_biblioteca(response.getId_biblioteca());
        model.setNombre(response.getNombre());
        model.setDireccion(response.getDireccion());
        model.setLatitud(response.getLatitud());
        model.setLongitud(response.getLongitud());
        model.setHorario_atencion(response.getHorario_atencion());
        model.setId_facultad(response.getId_facultad());
        bibliotecaDao.save(model);
        return modelMapper.map(model, bibliotecaDtoResponse.class);
    }

    @Override
    @Transactional
    public List<bibliotecaDtoResponse> buscarPorNombre(String nombre) {
        return bibliotecaDao.findByNombreLike(nombre).stream()
                .map(b -> modelMapper.map(b, bibliotecaDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<bibliotecaDtoResponse> buscarPorFacultad(Long idFacultad) {
        return bibliotecaDao.findByFacultad(idFacultad).stream()
                .map(b -> modelMapper.map(b, bibliotecaDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<bibliotecaDtoResponse> buscarPorHorario(String horario) {
        return bibliotecaDao.findByHorarioAtencion(horario).stream()
                .map(b -> modelMapper.map(b, bibliotecaDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long contarPorFacultad(Long idFacultad) {
        return bibliotecaDao.countByFacultad(idFacultad);
    }
    
    @Transactional
    public bibliotecaDtoResponse deleteBiblioteca(bibliotecaDtoResponse bibliotecaDtoResponse) {
        bibliotecaModel biblioteca = bibliotecaDao.findById(bibliotecaDtoResponse.getId_biblioteca())
            .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada con ID: " + bibliotecaDtoResponse.getId_biblioteca()));

        bibliotecaDao.delete(biblioteca);

        return modelMapper.map(biblioteca, bibliotecaDtoResponse.class);
    }

    @Override
    public List<bibliotecaModel> buscarPorUsuario(Long idUsuario) {
        return bibliotecaDao.findByUsuario(idUsuario);
    }

    @Override
    public Long contarBibliotecasPorUsuario(Long idUsuario) {
        return bibliotecaDao.countByIdUsuario(idUsuario);
    }

    @Override
    public bibliotecaModel buscarPorId(Long idBiblioteca) {
        return bibliotecaDao.findById(idBiblioteca)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada"));
    }
}
