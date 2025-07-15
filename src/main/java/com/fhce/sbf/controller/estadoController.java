package com.fhce.sbf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(service.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar estados: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-tipo")
    public ResponseEntity<?> buscarPorTipo(@RequestParam String tipo) {
        try {
            return ResponseEntity.ok(service.buscarPorTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Tipo no encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-tipo-like")
    public ResponseEntity<?> buscarPorTipoContiene(@RequestParam String tipo) {
        try {
            return ResponseEntity.ok(service.buscarPorTipoContiene(tipo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar tipo: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-id")
    public ResponseEntity<?> buscarPorId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Estado con ID no encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/nombres")
    public ResponseEntity<?> nombres() {
        try {
            return ResponseEntity.ok(service.listarNombresEstados());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar nombres: " + e.getMessage());
        }
    }

    @GetMapping("/contar")
    public ResponseEntity<?> contar() {
        try {
            return ResponseEntity.ok(service.contarEstados());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al contar estados: " + e.getMessage());
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody estadoDtoRequest req) {
        try {
            return new ResponseEntity<>(service.agregarEstado(req), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al agregar estado: " + e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editar(@RequestBody estadoDtoResponse res) {
        try {
            return ResponseEntity.ok(service.editarEstado(res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al editar estado: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestBody estadoDtoResponse res) {
        try {
            return ResponseEntity.ok(service.eliminarEstado(res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al eliminar estado: " + e.getMessage());
        }
    }
}
