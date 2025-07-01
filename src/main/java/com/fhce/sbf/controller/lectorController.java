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

    @GetMapping("/buscar/ci")
    public ResponseEntity<lectorDtoResponse> buscarPorCi(@RequestParam int ci) {
        return ResponseEntity.ok(lectorService.findByCi(ci));
    }

    @GetMapping("/buscar/ru")
    public ResponseEntity<lectorDtoResponse> buscarPorRu(@RequestParam int ru) {
        return ResponseEntity.ok(lectorService.findByRu(ru));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<lectorDtoResponse>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(lectorService.findByNombre(nombre));
    }

    @GetMapping("/buscar/nombre-like")
    public ResponseEntity<List<lectorDtoResponse>> buscarNombreLike(@RequestParam String nombre) {
        return ResponseEntity.ok(lectorService.findByNombreLike(nombre));
    }

    @GetMapping("/buscar/carrera")
    public ResponseEntity<List<lectorDtoResponse>> buscarPorCarrera(@RequestParam String carrera) {
        return ResponseEntity.ok(lectorService.findByCarrera(carrera));
    }

    @GetMapping("/contar/carrera")
    public ResponseEntity<Long> contarPorCarrera(@RequestParam String carrera) {
        return ResponseEntity.ok(lectorService.countByCarrera(carrera));
    }
}
