package com.fhce.sbf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.lectorDtoRequest;
import com.fhce.sbf.dto.lectorDtoResponse;
import com.fhce.sbf.service.lectorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/lector")
@RequiredArgsConstructor
public class lectorController {

    private final lectorService lectorService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(lectorService.listarLectores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar lectores: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody lectorDtoRequest request) {
        try {
            return new ResponseEntity<>(lectorService.addLector(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al agregar lector: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody lectorDtoResponse response) {
        try {
            return ResponseEntity.ok(lectorService.editLector(response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al editar lector: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody lectorDtoResponse response) {
        try {
            return ResponseEntity.ok(lectorService.deleteLector(response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar lector: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/ci")
    public ResponseEntity<?> buscarPorCi(@RequestParam int ci) {
        try {
            return ResponseEntity.ok(lectorService.findByCi(ci));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lector con CI no encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/ru")
    public ResponseEntity<?> buscarPorRu(@RequestParam int ru) {
        try {
            return ResponseEntity.ok(lectorService.findByRu(ru));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lector con RU no encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(lectorService.findByNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por nombre: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/nombre-like")
    public ResponseEntity<?> buscarNombreLike(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(lectorService.findByNombreLike(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por nombre (like): " + e.getMessage());
        }
    }

    @GetMapping("/buscar/carrera")
    public ResponseEntity<?> buscarPorCarrera(@RequestParam String carrera) {
        try {
            return ResponseEntity.ok(lectorService.findByCarrera(carrera));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por carrera: " + e.getMessage());
        }
    }

    @GetMapping("/contar/carrera")
    public ResponseEntity<?> contarPorCarrera(@RequestParam String carrera) {
        try {
            return ResponseEntity.ok(lectorService.countByCarrera(carrera));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al contar por carrera: " + e.getMessage());
        }
    }
}
