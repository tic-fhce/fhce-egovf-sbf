package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.tiene_eDtoRequest;
import com.fhce.sbf.dto.tiene_eDtoResponse;
import com.fhce.sbf.service.tiene_eService;

@RestController
@RequestMapping("/api/tiene_e")
public class tiene_eController {

    @Autowired
    private tiene_eService service;

    @PostMapping
    public tiene_eDtoResponse save(@RequestBody tiene_eDtoRequest request) {
        return service.save(request);
    }

    @GetMapping
    public List<tiene_eDtoResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/ejemplar/{codigo}")
    public List<tiene_eDtoResponse> buscarEstadosPorEjemplar(@PathVariable Long codigo) {
        return service.buscarEstadosPorEjemplar(codigo);
    }

    @GetMapping("/estado/{idEstado}")
    public List<tiene_eDtoResponse> buscarEjemplaresPorEstado(@PathVariable Long idEstado) {
        return service.buscarEjemplaresPorEstado(idEstado);
    }

    @GetMapping("/contar/estado/{idEstado}")
    public Long contarEjemplaresPorEstado(@PathVariable Long idEstado) {
        return service.contarEjemplaresPorEstado(idEstado);
    }

    @GetMapping("/existe/{codigo}/{idEstado}")
    public Long existeRelacion(@PathVariable Long codigo, @PathVariable Long idEstado) {
        return service.existeRelacion(codigo, idEstado);
    }

    @GetMapping("/ver-estados/{codigo}")
    public List<Object[]> verEstadosDeEjemplar(@PathVariable Long codigo) {
        return service.verEstadosDeEjemplar(codigo);
    }

    @GetMapping("/listar-todo")
    public List<Object[]> listarEjemplaresConEstadoYLibro() {
        return service.listarEjemplaresConEstadoYLibro();
    }

    @GetMapping("/contar-estados/{codigo}")
    public Long contarEstadosDeEjemplar(@PathVariable Long codigo) {
        return service.contarEstadosDeEjemplar(codigo);
    }

    @DeleteMapping
    public tiene_eDtoResponse delete(@RequestBody tiene_eDtoResponse response) {
        return service.delete(response);
    }
}
