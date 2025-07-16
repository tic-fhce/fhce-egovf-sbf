package com.fhce.sbf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.esta_enDtoRequest;
import com.fhce.sbf.service.esta_enService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/esta_en")
@RequiredArgsConstructor
public class esta_enController {

    private final esta_enService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody esta_enDtoRequest request) {
        try {
            return new ResponseEntity<>(service.save(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al agregar relación libro-préstamo: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar relaciones: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/libro")
    public ResponseEntity<?> buscarPorLibro(@RequestParam Long idLibro) {
        try {
            return ResponseEntity.ok(service.buscarPorLibro(idLibro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por libro: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/prestamo")
    public ResponseEntity<?> buscarPorPrestamo(@RequestParam Long idPrestamo) {
        try {
            return ResponseEntity.ok(service.buscarPorPrestamo(idPrestamo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por préstamo: " + e.getMessage());
        }
    }

    @GetMapping("/contar/prestamo")
    public ResponseEntity<?> contarLibrosEnPrestamo(@RequestParam Long idPrestamo) {
        try {
            return ResponseEntity.ok(service.contarLibrosEnPrestamo(idPrestamo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al contar libros en préstamo: " + e.getMessage());
        }
    }

    @GetMapping("/existe")
    public ResponseEntity<?> existeRelacion(@RequestParam Long idLibro, @RequestParam Long idPrestamo) {
        try {
            return ResponseEntity.ok(service.existeRelacion(idLibro, idPrestamo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al verificar existencia: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long idLibro, @RequestParam Long idPrestamo) {
        try {
            return ResponseEntity.ok(service.delete(idLibro, idPrestamo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar relación: " + e.getMessage());
        }
    }

    @GetMapping("/listar-relaciones")
    public ResponseEntity<?> listarRelaciones() {
        try {
            return ResponseEntity.ok(service.listarRelaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar relaciones personalizadas: " + e.getMessage());
        }
    }
}
