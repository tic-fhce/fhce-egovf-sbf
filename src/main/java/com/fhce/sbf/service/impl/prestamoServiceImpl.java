package com.fhce.sbf.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sbf.dao.prestamoDao;
import com.fhce.sbf.dto.prestamoDtoRequest;
import com.fhce.sbf.dto.prestamoDtoResponse;
import com.fhce.sbf.model.prestamoModel;
import com.fhce.sbf.service.prestamoService;

@Service
public class prestamoServiceImpl implements prestamoService {

    @Autowired
    private prestamoDao dao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public prestamoDtoResponse save(prestamoDtoRequest request) {
        prestamoModel model = modelMapper.map(request, prestamoModel.class);
        return modelMapper.map(dao.save(model), prestamoDtoResponse.class);
    }

    @Override
    public List<prestamoDtoResponse> findAll() {
        return dao.listarTodos().stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<prestamoDtoResponse> buscarPorLector(Long idLector) {
        return dao.buscarPorLector(idLector).stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<prestamoDtoResponse> buscarPorFechaPrestamo(LocalDate fecha) {
        return dao.buscarPorFechaPrestamo(fecha).stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<prestamoDtoResponse> buscarVencidos() {
        return dao.buscarVencidos().stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<prestamoDtoResponse> buscarActivos() {
        return dao.buscarActivos().stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<prestamoDtoResponse> buscarEntreFechas(LocalDate desde, LocalDate hasta) {
        return dao.buscarEntreFechas(desde, hasta).stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long contarPorLector(Long idLector) {
        return dao.contarPorLector(idLector);
    }

    @Override
    public Long contarPrestamos() {
        return dao.contarPrestamos();
    }

    @Override
    public List<Object[]> listarPrestamosConNombreLector() {
        return dao.listarPrestamosConNombreLector();
    }

    @Override
    public List<Object[]> contarPrestamosPorLector() {
        return dao.contarPrestamosPorLector();
    }

    @Override
    public List<prestamoDtoResponse> buscarPrestamosSinDevolucion() {
        return dao.buscarPrestamosSinDevolucion().stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<prestamoDtoResponse> buscarPrestamosDeHoy() {
        return dao.buscarPrestamosDeHoy().stream()
                .map(model -> modelMapper.map(model, prestamoDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long contarPrestamosDeHoy() {
        return dao.contarPrestamosDeHoy();
    }

    @Override
    public Long contarPrestamosVencidos() {
        return dao.contarPrestamosVencidos();
    }

    @Override
    public Long contarPrestamosActivos() {
        return dao.contarPrestamosActivos();
    }

    @Override
    @Transactional
    public prestamoDtoResponse delete(prestamoDtoResponse prestamoDtoResponse) {
        prestamoModel model = dao.findById(prestamoDtoResponse.getId_prestamo())
            .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + prestamoDtoResponse.getId_prestamo()));
        dao.delete(model);
        return modelMapper.map(model, prestamoDtoResponse.class);
    }
    
    @Override
    @Transactional
    public prestamoDtoResponse edit(prestamoDtoResponse dto) {
        prestamoModel existente = dao.findById(dto.getId_prestamo())
            .orElseThrow(() -> new RuntimeException("No se encontró el préstamo con ID: " + dto.getId_prestamo()));

        existente.setFecha_pres(dto.getFecha_pres());
        existente.setFecha_dev(dto.getFecha_dev());
        existente.setId_lector(dto.getId_lector());

        dao.save(existente);
        return modelMapper.map(existente, prestamoDtoResponse.class);
    }

    @Autowired
    private com.fhce.sbf.dao.usuarioDao usuarioDao;

    @Autowired
    private com.fhce.sbf.dao.lectorDao lectorDao;
    @Override
    public List<prestamoModel> buscarPorLectorSiExiste(Long id) {
        if (lectorDao.existsById(id)) {
            return dao.buscarPrestamosPorLector(id);
        } else if (usuarioDao.existsById(id)) {
            throw new RuntimeException("Este ID pertenece a un usuario administrador, use el endpoint de bibliotecas.");
        } else {
            throw new RuntimeException("ID no registrado como lector ni como usuario.");
        }
    }

    
    @Override
    public List<prestamoModel> buscarPrestamosPorUsuarioAdmin(Long idUsuario) {
        return dao.findPrestamosByUsuarioAdmin(idUsuario);
    }

    @Override
    public Long contarPrestamosPorUsuarioAdmin(Long idUsuario) {
        return dao.countPrestamosByUsuarioAdmin(idUsuario);
    }

    @Override
    public List<prestamoModel> buscarPrestamosActivosPorUsuarioAdmin(Long idUsuario) {
        return dao.findPrestamosActivosByUsuarioAdmin(idUsuario);
    }

    @Override
    public List<prestamoModel> buscarPrestamosVencidosPorUsuarioAdmin(Long idUsuario) {
        return dao.findPrestamosVencidosByUsuarioAdmin(idUsuario);
    }

}