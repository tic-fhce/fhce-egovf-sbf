package com.fhce.sbf.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(@RequestBody prestamoDtoRequest request) {
        try {
            return new ResponseEntity<>(service.save(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar préstamo: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar préstamos: " + e.getMessage());
        }
    }

    @GetMapping("/lector")
    public ResponseEntity<?> byLector(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorLector(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al buscar por lector: " + e.getMessage());
        }
    }

    @GetMapping("/fecha")
    public ResponseEntity<?> byFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            return ResponseEntity.ok(service.buscarPorFechaPrestamo(fecha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al buscar por fecha: " + e.getMessage());
        }
    }

    @GetMapping("/vencidos")
    public ResponseEntity<?> vencidos() {
        try {
            return ResponseEntity.ok(service.buscarVencidos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar vencidos: " + e.getMessage());
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<?> activos() {
        try {
            return ResponseEntity.ok(service.buscarActivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar activos: " + e.getMessage());
        }
    }

    @GetMapping("/rango")
    public ResponseEntity<?> porRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        try {
            return ResponseEntity.ok(service.buscarEntreFechas(desde, hasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al buscar en el rango: " + e.getMessage());
        }
    }

    @GetMapping("/sin-devolucion")
    public ResponseEntity<?> sinDevolucion() {
        try {
            return ResponseEntity.ok(service.buscarPrestamosSinDevolucion());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar sin devolución: " + e.getMessage());
        }
    }

    @GetMapping("/hoy")
    public ResponseEntity<?> deHoy() {
        try {
            return ResponseEntity.ok(service.buscarPrestamosDeHoy());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar préstamos de hoy: " + e.getMessage());
        }
    }

    @GetMapping("/contar/lector")
    public ResponseEntity<?> contarPorLector(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.contarPorLector(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al contar préstamos del lector: " + e.getMessage());
        }
    }

    @GetMapping("/contar/total")
    public ResponseEntity<?> total() {
        try {
            return ResponseEntity.ok(service.contarPrestamos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al contar préstamos totales: " + e.getMessage());
        }
    }

    @GetMapping("/contar/hoy")
    public ResponseEntity<?> contarHoy() {
        try {
            return ResponseEntity.ok(service.contarPrestamosDeHoy());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al contar préstamos de hoy: " + e.getMessage());
        }
    }

    @GetMapping("/contar/vencidos")
    public ResponseEntity<?> contarVencidos() {
        try {
            return ResponseEntity.ok(service.contarPrestamosVencidos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al contar préstamos vencidos: " + e.getMessage());
        }
    }

    @GetMapping("/contar/activos")
    public ResponseEntity<?> contarActivos() {
        try {
            return ResponseEntity.ok(service.contarPrestamosActivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al contar préstamos activos: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody prestamoDtoResponse prestamo) {
        try {
            return ResponseEntity.ok(service.delete(prestamo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar préstamo: " + e.getMessage());
        }
    }
}
