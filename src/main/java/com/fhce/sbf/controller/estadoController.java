package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.estadoDtoRequest;
import com.fhce.sbf.dto.estadoDtoResponse;
import com.fhce.sbf.service.estadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor
public class estadoController {

    private final estadoService service;

    @GetMapping("/listar")
    public List<estadoDtoResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/buscar-tipo")
    public estadoDtoResponse buscarPorTipo(@RequestParam String tipo) {
        return service.buscarPorTipo(tipo);
    }

    @GetMapping("/buscar-tipo-like")
    public List<estadoDtoResponse> buscarPorTipoContiene(@RequestParam String tipo) {
        return service.buscarPorTipoContiene(tipo);
    }

    @GetMapping("/buscar-id")
    public estadoDtoResponse buscarPorId(@RequestParam Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/nombres")
    public List<String> nombres() {
        return service.listarNombresEstados();
    }

    @GetMapping("/contar")
    public Long contar() {
        return service.contarEstados();
    }

    @PostMapping("/agregar")
    public estadoDtoResponse agregar(@RequestBody estadoDtoRequest req) {
        return service.agregarEstado(req);
    }

    @PutMapping("/editar")
    public estadoDtoResponse editar(@RequestBody estadoDtoResponse res) {
        return service.editarEstado(res);
    }

    @DeleteMapping("/eliminar")
    public estadoDtoResponse eliminar(@RequestBody estadoDtoResponse res) {
        return service.eliminarEstado(res);
    }
}
