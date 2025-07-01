package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.unidadDtoRequest;
import com.fhce.sbf.dto.unidadDtoResponse;
import com.fhce.sbf.service.unidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/unidad")
@RequiredArgsConstructor
public class unidadController {

    private final unidadService unidadService;

    @GetMapping("/listar")
    public ResponseEntity<List<unidadDtoResponse>> listarUnidades() {
        return ResponseEntity.ok(unidadService.listarUnidades());
    }

    @PostMapping("/add")
    public ResponseEntity<unidadDtoResponse> addUnidad(@RequestBody unidadDtoRequest request) {
        return new ResponseEntity<>(unidadService.addUnidad(request), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<unidadDtoResponse> editUnidad(@RequestBody unidadDtoResponse response) {
        return ResponseEntity.ok(unidadService.editUnidad(response));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<unidadDtoResponse> deleteUnidad(@RequestBody unidadDtoResponse response) {
        return ResponseEntity.ok(unidadService.deleteUnidad(response));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<unidadDtoResponse> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(unidadService.buscarPorNombre(nombre));
    }

    @GetMapping("/buscar/nombre-like")
    public ResponseEntity<List<unidadDtoResponse>> buscarPorNombreLike(@RequestParam String nombre) {
        return ResponseEntity.ok(unidadService.buscarPorNombreLike(nombre));
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<unidadDtoResponse>> buscarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(unidadService.buscarPorTipo(tipo));
    }

    @GetMapping("/buscar/facultad")
    public ResponseEntity<List<unidadDtoResponse>> buscarPorFacultad(@RequestParam Long idFacultad) {
        return ResponseEntity.ok(unidadService.buscarPorFacultad(idFacultad));
    }

    @GetMapping("/contar/tipo")
    public ResponseEntity<Long> contarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(unidadService.contarPorTipo(tipo));
    }

    @GetMapping("/listar-con-facultad")
    public ResponseEntity<List<Object[]>> listarConFacultad() {
        return ResponseEntity.ok(unidadService.listarUnidadesConFacultad());
    }
}
