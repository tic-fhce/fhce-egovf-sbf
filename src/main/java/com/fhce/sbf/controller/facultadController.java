package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.facultadDtoRequest;
import com.fhce.sbf.dto.facultadDtoResponse;
import com.fhce.sbf.service.facultadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/facultad")
@RequiredArgsConstructor
public class facultadController {

    private final facultadService facultadService;

    @GetMapping("/listar_facultades")
    public ResponseEntity<List<facultadDtoResponse>> listar_facultades() {
        try {
            return new ResponseEntity<>(facultadService.listar_facultades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addFacultad")
    public ResponseEntity<facultadDtoResponse> addFacultad(@RequestBody facultadDtoRequest facultadDtoRequest) {
        try {
            return new ResponseEntity<>(facultadService.addFacultad(facultadDtoRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editFacultad")
    public ResponseEntity<facultadDtoResponse> editFacultad(@RequestBody facultadDtoResponse facultadDtoResponse) {
        try {
            return new ResponseEntity<>(facultadService.editFacultad(facultadDtoResponse), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la facultad: " + e.getMessage());
        }
    }

    // 🔍 Buscar facultad por nombre exacto
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<facultadDtoResponse> buscarPorNombre(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(facultadService.buscarPorNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 🔎 Buscar facultades por coincidencia
    @GetMapping("/buscar/contiene/{nombre}")
    public ResponseEntity<List<facultadDtoResponse>> buscarPorNombreContiene(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(facultadService.buscarPorNombreContiene(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 🧮 Contar cuántas facultades hay
    @GetMapping("/contar")
    public ResponseEntity<Long> contarFacultades() {
        try {
            return new ResponseEntity<>(facultadService.contarFacultades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 🧾 Listar solo los nombres de las facultades
    @GetMapping("/listar_nombres")
    public ResponseEntity<List<String>> listarNombres() {
        try {
            return new ResponseEntity<>(facultadService.listarNombres(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteFacultad")
    public ResponseEntity<facultadDtoResponse> deleteFacultad(@RequestBody facultadDtoResponse facultadDtoResponse) {
        try {
            return new ResponseEntity<>(facultadService.deleteFacultad(facultadDtoResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
