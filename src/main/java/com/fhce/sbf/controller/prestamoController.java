package com.fhce.sbf.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.prestamoDtoRequest;
import com.fhce.sbf.dto.prestamoDtoResponse;
import com.fhce.sbf.service.prestamoService;

@RestController
@RequestMapping("/api/prestamo")
public class prestamoController {

    @Autowired
    private prestamoService service;

    @PostMapping("/save")
    public prestamoDtoResponse save(@RequestBody prestamoDtoRequest request) {
        return service.save(request);
    }

    @GetMapping("/all")
    public List<prestamoDtoResponse> all() {
        return service.findAll();
    }

    @GetMapping("/lector")
    public List<prestamoDtoResponse> byLector(@RequestParam Long id) {
        return service.buscarPorLector(id);
    }

    @GetMapping("/fecha")
    public List<prestamoDtoResponse> byFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return service.buscarPorFechaPrestamo(fecha);
    }

    @GetMapping("/vencidos")
    public List<prestamoDtoResponse> vencidos() {
        return service.buscarVencidos();
    }

    @GetMapping("/activos")
    public List<prestamoDtoResponse> activos() {
        return service.buscarActivos();
    }

    @GetMapping("/rango")
    public List<prestamoDtoResponse> porRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return service.buscarEntreFechas(desde, hasta);
    }

    @GetMapping("/sin-devolucion")
    public List<prestamoDtoResponse> sinDevolucion() {
        return service.buscarPrestamosSinDevolucion();
    }

    @GetMapping("/hoy")
    public List<prestamoDtoResponse> deHoy() {
        return service.buscarPrestamosDeHoy();
    }

    @GetMapping("/contar/lector")
    public Long contarPorLector(@RequestParam Long id) {
        return service.contarPorLector(id);
    }

    @GetMapping("/contar/total")
    public Long total() {
        return service.contarPrestamos();
    }

    @GetMapping("/contar/hoy")
    public Long contarHoy() {
        return service.contarPrestamosDeHoy();
    }

    @GetMapping("/contar/vencidos")
    public Long contarVencidos() {
        return service.contarPrestamosVencidos();
    }

    @GetMapping("/contar/activos")
    public Long contarActivos() {
        return service.contarPrestamosActivos();
    }

    @DeleteMapping("/delete")
    public prestamoDtoResponse delete(@RequestBody prestamoDtoResponse prestamo) {
        return service.delete(prestamo);
    }
}
