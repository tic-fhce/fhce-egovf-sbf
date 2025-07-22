package com.fhce.sbf.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.tiene_eDtoRequest;
import com.fhce.sbf.service.tiene_eService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/tiene_e")
@RequiredArgsConstructor
public class tiene_eController {

    private final tiene_eService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody tiene_eDtoRequest request) {
        try {
            return new ResponseEntity<>(service.save(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar relación: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar relaciones: " + e.getMessage());
        }
    }

    @GetMapping("/ejemplar")
    public ResponseEntity<?> buscarEstadosPorEjemplar(@RequestParam Long codigo) {
        try {
            return ResponseEntity.ok(service.buscarEstadosPorEjemplar(codigo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al buscar estados por ejemplar: " + e.getMessage());
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> buscarEjemplaresPorEstado(@RequestParam Long idEstado) {
        try {
            return ResponseEntity.ok(service.buscarEjemplaresPorEstado(idEstado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al buscar ejemplares por estado: " + e.getMessage());
        }
    }

    @GetMapping("/contar/estado")
    public ResponseEntity<?> contarEjemplaresPorEstado(@RequestParam Long idEstado) {
        try {
            return ResponseEntity.ok(service.contarEjemplaresPorEstado(idEstado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al contar ejemplares: " + e.getMessage());
        }
    }

    @GetMapping("/existe")
    public ResponseEntity<?> existeRelacion(@RequestParam Long codigo, @RequestParam Long idEstado) {
        try {
            return ResponseEntity.ok(service.existeRelacion(codigo, idEstado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al verificar relación: " + e.getMessage());
        }
    }

    @GetMapping("/ver-estados")
    public ResponseEntity<?> verEstadosDeEjemplar(@RequestParam Long codigo) {
        try {
            return ResponseEntity.ok(service.verEstadosDeEjemplar(codigo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al ver estados: " + e.getMessage());
        }
    }

    @GetMapping("/listar-todo")
    public ResponseEntity<?> listarEjemplaresConEstadoYLibro() {
        try {
            return ResponseEntity.ok(service.listarEjemplaresConEstadoYLibro());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar todo: " + e.getMessage());
        }
    }

    @GetMapping("/contar-estados")
    public ResponseEntity<?> contarEstadosDeEjemplar(@RequestParam Long codigo) {
        try {
            return ResponseEntity.ok(service.contarEstadosDeEjemplar(codigo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al contar estados del ejemplar: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> delete(@RequestParam Long codigo, @RequestParam Long id_estado) {
        try {
            return ResponseEntity.ok(service.delete(codigo, id_estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar relación: " + e.getMessage());
        }
    }
    
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody tiene_eDtoRequest dto) {
        try {
            return new ResponseEntity<>(service.editTiene_e(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al editar la relación: " + e.getMessage());
        }
    }
}
