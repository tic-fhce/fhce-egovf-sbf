package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.lectorDtoRequest;
import com.fhce.sbf.dto.lectorDtoResponse;
import com.fhce.sbf.service.lectorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-sbf/lector")
@RequiredArgsConstructor
public class lectorController {

    private final lectorService lectorService;

    @GetMapping("/listar")
    public ResponseEntity<List<lectorDtoResponse>> listar() {
        return ResponseEntity.ok(lectorService.listarLectores());
    }

    @PostMapping("/add")
    public ResponseEntity<lectorDtoResponse> add(@RequestBody lectorDtoRequest request) {
        return new ResponseEntity<>(lectorService.addLector(request), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<lectorDtoResponse> edit(@RequestBody lectorDtoResponse response) {
        return ResponseEntity.ok(lectorService.editLector(response));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<lectorDtoResponse> delete(@RequestBody lectorDtoResponse response) {
        return ResponseEntity.ok(lectorService.deleteLector(response));
    }

    @GetMapping("/buscar/ci/{ci}")
    public ResponseEntity<lectorDtoResponse> buscarPorCi(@PathVariable int ci) {
        return ResponseEntity.ok(lectorService.findByCi(ci));
    }

    @GetMapping("/buscar/ru/{ru}")
    public ResponseEntity<lectorDtoResponse> buscarPorRu(@PathVariable int ru) {
        return ResponseEntity.ok(lectorService.findByRu(ru));
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<lectorDtoResponse>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(lectorService.findByNombre(nombre));
    }

    @GetMapping("/buscar/nombre-like/{nombre}")
    public ResponseEntity<List<lectorDtoResponse>> buscarNombreLike(@PathVariable String nombre) {
        return ResponseEntity.ok(lectorService.findByNombreLike(nombre));
    }

    @GetMapping("/buscar/carrera/{carrera}")
    public ResponseEntity<List<lectorDtoResponse>> buscarPorCarrera(@PathVariable String carrera) {
        return ResponseEntity.ok(lectorService.findByCarrera(carrera));
    }

    @GetMapping("/contar/carrera/{carrera}")
    public ResponseEntity<Long> contarPorCarrera(@PathVariable String carrera) {
        return ResponseEntity.ok(lectorService.countByCarrera(carrera));
    }
}
