package com.fhce.sbf.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.sbf.dao.libroDao;
import com.fhce.sbf.dto.libroDtoRequest;
import com.fhce.sbf.dto.libroDtoResponse;
import com.fhce.sbf.model.libroModel;
import com.fhce.sbf.service.libroService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class libroServiceImpl implements libroService {
    private final libroDao dao;
    private final ModelMapper mapper;

    @Transactional
    public List<libroDtoResponse> listarLibros() {
        return dao.listarLibros().stream()
            .map(l -> mapper.map(l, libroDtoResponse.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public libroDtoResponse addLibro(libroDtoRequest dto) {
        libroModel l = mapper.map(dto, libroModel.class);
        dao.save(l);
        return mapper.map(l, libroDtoResponse.class);
    }

    @Transactional
    public libroDtoResponse editLibro(libroDtoResponse dto) {
        libroModel l = mapper.map(dto, libroModel.class);
        dao.save(l);
        return mapper.map(l, libroDtoResponse.class);
    }

    @Transactional
    public libroDtoResponse deleteLibro(libroDtoResponse dto) {
        libroModel l = dao.findById(dto.getId_libro())
            .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + dto.getId_libro()));
        dao.delete(l);
        return mapper.map(l, libroDtoResponse.class);
    }

    @Transactional
    public List<libroDtoResponse> buscarPorAutor(String autor) {
        return dao.findByAutor(autor).stream().map(l -> mapper.map(l, libroDtoResponse.class)).collect(Collectors.toList());
    }

    @Transactional
    public List<libroDtoResponse> buscarPorIdioma(String idioma) {
        return dao.findByIdioma(idioma).stream().map(l -> mapper.map(l, libroDtoResponse.class)).collect(Collectors.toList());
    }

    @Transactional
    public List<libroDtoResponse> buscarPorBiblioteca(Long id) {
        return dao.findByBiblioteca(id).stream().map(l -> mapper.map(l, libroDtoResponse.class)).collect(Collectors.toList());
    }

    @Transactional
    public List<libroDtoResponse> buscarPorUsuario(Long id) {
        return dao.findByUsuario(id).stream().map(l -> mapper.map(l, libroDtoResponse.class)).collect(Collectors.toList());
    }

    @Transactional
    public List<libroDtoResponse> buscarPorTitulo(String titulo) {
        return dao.findByTituloLike(titulo).stream().map(l -> mapper.map(l, libroDtoResponse.class)).collect(Collectors.toList());
    }

    @Transactional
    public List<libroDtoResponse> buscarPorAnio(int anio) {
        return dao.findByAnio(anio).stream().map(l -> mapper.map(l, libroDtoResponse.class)).collect(Collectors.toList());
    }
    @Override
    public List<Object[]> obtenerBibliotecasConLibrosPorUsuario(Long idUsuario) {
        return dao.obtenerBibliotecasConLibrosPorUsuario(idUsuario);
    }

}