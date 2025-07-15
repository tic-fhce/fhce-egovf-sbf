package com.fhce.sbf.controller;


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
    public ResponseEntity<?> listarUnidades() {
        try {
            return ResponseEntity.ok(unidadService.listarUnidades());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar unidades: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUnidad(@RequestBody unidadDtoRequest request) {
        try {
            return new ResponseEntity<>(unidadService.addUnidad(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al agregar unidad: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUnidad(@RequestBody unidadDtoResponse response) {
        try {
            return ResponseEntity.ok(unidadService.editUnidad(response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al editar unidad: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUnidad(@RequestBody unidadDtoResponse response) {
        try {
            return ResponseEntity.ok(unidadService.deleteUnidad(response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al eliminar unidad: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(unidadService.buscarPorNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Unidad no encontrada con nombre: " + nombre);
        }
    }

    @GetMapping("/buscar/nombre-like")
    public ResponseEntity<?> buscarPorNombreLike(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(unidadService.buscarPorNombreLike(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar unidades por nombre parcial: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<?> buscarPorTipo(@RequestParam String tipo) {
        try {
            return ResponseEntity.ok(unidadService.buscarPorTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar unidades por tipo: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/facultad")
    public ResponseEntity<?> buscarPorFacultad(@RequestParam Long idFacultad) {
        try {
            return ResponseEntity.ok(unidadService.buscarPorFacultad(idFacultad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar unidades por facultad: " + e.getMessage());
        }
    }

    @GetMapping("/contar/tipo")
    public ResponseEntity<?> contarPorTipo(@RequestParam String tipo) {
        try {
            return ResponseEntity.ok(unidadService.contarPorTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al contar unidades por tipo: " + e.getMessage());
        }
    }

    @GetMapping("/listar-con-facultad")
    public ResponseEntity<?> listarConFacultad() {
        try {
            return ResponseEntity.ok(unidadService.listarUnidadesConFacultad());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar unidades con facultad: " + e.getMessage());
        }
    }
}
