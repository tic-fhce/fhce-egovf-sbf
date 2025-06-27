package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.bibliotecaDtoRequest;
import com.fhce.sbf.dto.bibliotecaDtoResponse;
import com.fhce.sbf.service.bibliotecaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/biblioteca")
@RequiredArgsConstructor
public class bibliotecaController {

    private final bibliotecaService bibliotecaService;

    @GetMapping("/listar_bibliotecas")
    public ResponseEntity<List<bibliotecaDtoResponse>> listar() {
        try {
            return new ResponseEntity<>(bibliotecaService.listarBibliotecas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBiblioteca")
    public ResponseEntity<bibliotecaDtoResponse> add(@RequestBody bibliotecaDtoRequest request) {
        try {
            return new ResponseEntity<>(bibliotecaService.addBiblioteca(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editBiblioteca")
    public ResponseEntity<bibliotecaDtoResponse> edit(@RequestBody bibliotecaDtoResponse response) {
        try {
            return new ResponseEntity<>(bibliotecaService.editBiblioteca(response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<bibliotecaDtoResponse>> buscarPorNombre(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(bibliotecaService.buscarPorNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/facultad/{idFacultad}")
    public ResponseEntity<List<bibliotecaDtoResponse>> buscarPorFacultad(@PathVariable Long idFacultad) {
        try {
            return new ResponseEntity<>(bibliotecaService.buscarPorFacultad(idFacultad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/horario/{horario}") //requestparam
    public ResponseEntity<List<bibliotecaDtoResponse>> buscarPorHorario(@PathVariable String horario) {
        try {
            return new ResponseEntity<>(bibliotecaService.buscarPorHorario(horario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contar/facultad/{idFacultad}")
    public ResponseEntity<Long> contarPorFacultad(@PathVariable Long idFacultad) {
        try {
            return new ResponseEntity<>(bibliotecaService.contarPorFacultad(idFacultad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteBiblioteca")
    public ResponseEntity<bibliotecaDtoResponse> deleteBiblioteca(@RequestBody bibliotecaDtoResponse bibliotecaDtoResponse) {
        try {
            return new ResponseEntity<>(bibliotecaService.deleteBiblioteca(bibliotecaDtoResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
